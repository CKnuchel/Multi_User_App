package com.wiss.m223.Controller;

import com.wiss.m223.Config.UserDetailsImpl;
import com.wiss.m223.DTO.JwtResponse;
import com.wiss.m223.DTO.MessageResponse;
import com.wiss.m223.Model.Role;
import com.wiss.m223.Model.Role.ERole;
import com.wiss.m223.Model.User;
import com.wiss.m223.Repository.RoleRepository;
import com.wiss.m223.Repository.UserRepository;
import com.wiss.m223.Requests.LoginRequest;
import com.wiss.m223.Requests.SignupRequest;
import com.wiss.m223.Util.JwtUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AuthController ist ein REST-Controller, der HTTP-Anfragen im Zusammenhang mit Authentifizierung und Registrierung behandelt.
 * Er verwendet die CrossOrigin-Anmerkung, um die Freigabe von Ressourcen über Ursprünge hinweg zu ermöglichen.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    /**
     * Diese Methode behandelt POST-Anfragen zur Authentifizierung eines Benutzers.
     * Sie nimmt eine LoginRequest als Parameter, authentifiziert den Benutzer und gibt ein JwtResponse-Objekt zurück.
     * @param loginRequest - die LoginRequest.
     * @return ResponseEntity<?> - ein JwtResponse-Objekt oder eine Fehlermeldung.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * Diese Methode behandelt POST-Anfragen zur Registrierung eines neuen Benutzers.
     * Sie nimmt eine SignupRequest als Parameter, erstellt einen neuen Benutzer und gibt eine Erfolgsmeldung zurück.
     * @param signUpRequest - die SignupRequest.
     * @return ResponseEntity<?> - eine Erfolgsmeldung oder eine Fehlermeldung.
     */
    @Transactional // registerUSer wird mit einer Transaktion ausgeführt (alles oder nichts)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user’s account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
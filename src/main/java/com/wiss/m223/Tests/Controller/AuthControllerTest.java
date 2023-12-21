package com.wiss.m223.Tests.Controller;

// Import-Anweisungen
import com.wiss.m223.Controller.AuthController;
import com.wiss.m223.DTO.MessageResponse;
import com.wiss.m223.Model.Role;
import com.wiss.m223.Repository.RoleRepository;
import com.wiss.m223.Repository.UserRepository;
import com.wiss.m223.Requests.SignupRequest;
import com.wiss.m223.Util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Diese Klasse testet die AuthController-Klasse
public class AuthControllerTest {

    // Erstellt eine Instanz der AuthController-Klasse und injiziert die Abhängigkeiten
    @InjectMocks
    private AuthController authController;

    // Mock-Objekte für die benötigten Abhängigkeiten
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private JwtUtils jwtUtils;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Mock-Objekte
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Dieser Test überprüft die Methode registerUser der AuthController-Klasse
    @Test
    public void registerUser_ShouldReturnMessageResponse() {
        // Erstellt eine neue SignupRequest-Instanz und setzt die erforderlichen Felder
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testUser");
        signupRequest.setEmail("testEmail");
        signupRequest.setPassword("testPassword");

        // Legt das Verhalten der Mock-Objekte fest, wenn sie aufgerufen werden
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(encoder.encode(any())).thenReturn("encodedPassword");
        when(roleRepository.findByName(any())).thenReturn(java.util.Optional.of(new Role()));

        // Ruft die Methode registerUser auf und speichert die Antwort
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Überprüft, ob der Statuscode der Antwort 200 ist und ob der Körper der Antwort eine Instanz von MessageResponse ist
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(MessageResponse.class, response.getBody().getClass());
    }
}
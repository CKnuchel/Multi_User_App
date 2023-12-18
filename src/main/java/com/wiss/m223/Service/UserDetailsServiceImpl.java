package com.wiss.m223.Service;

import com.wiss.m223.Config.UserDetailsImpl;
import com.wiss.m223.Model.Role;
import com.wiss.m223.Model.User;
import com.wiss.m223.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Definiert die Klasse als Service, was bedeutet, dass sie Geschäftslogik enthält
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    // Nutzt Autowiring, um die Abhängigkeit zu injizieren
    @Autowired
    UserRepository userRepository;

    // Überschreibt die Methode loadUserByUsername(), um einen Benutzer anhand seines Benutzernamens zu laden
    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        // Versucht, den Benutzer mit dem gegebenen Benutzernamen zu finden
        User user = userRepository.findByUsername(username)
                // Wirft eine Ausnahme, wenn der Benutzer nicht gefunden wurde
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Fügt die Rollen des Benutzers den Behörden hinzu
        for (Role r : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getName().toString()));
        }
        // Gibt die UserDetailsImpl des Benutzers zurück
        return UserDetailsImpl.build(user);
    }
}
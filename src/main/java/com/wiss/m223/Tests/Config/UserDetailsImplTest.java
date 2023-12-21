package com.wiss.m223.Tests.Config;

import com.wiss.m223.Config.UserDetailsImpl;
import com.wiss.m223.Model.Role;
import com.wiss.m223.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Diese Klasse enthält Unit-Tests für die UserDetailsImpl-Klasse
public class UserDetailsImplTest {

    // Instanzen von UserDetailsImpl und User, die in den Tests verwendet werden
    private UserDetailsImpl userDetails;
    private User user;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Testdaten
    @BeforeEach
    public void setUp() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Role.ERole.ROLE_USER));
        user = new User("testUser", "testEmail", "testPassword");
        user.setRoles(roles);
        userDetails = UserDetailsImpl.build(user);
    }

    // Dieser Test überprüft, ob die Methode build() korrekte UserDetailsImpl-Objekte erstellt
    @Test
    public void build_ShouldReturnUserDetailsWithCorrectAttributes() {
        assertEquals(user.getId(), userDetails.getId());
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getEmail(), userDetails.getEmail());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(role -> role.equals("ROLE_USER")));
    }

    // Dieser Test überprüft, ob die equals()-Methode true zurückgibt, wenn zwei UserDetailsImpl-Objekte gleich sind
    @Test
    public void equals_ShouldReturnTrueWhenComparingSameUserDetails() {
        UserDetailsImpl sameUserDetails = UserDetailsImpl.build(user);
        assertTrue(userDetails.equals(sameUserDetails));
    }

    // Dieser Test überprüft, ob die equals()-Methode false zurückgibt, wenn das Vergleichsobjekt null ist
    @Test
    public void equals_ShouldReturnFalseWhenComparingWithNull() {
        assertFalse(userDetails.equals(null));
    }

    // Dieser Test überprüft, ob die equals()-Methode false zurückgibt, wenn das Vergleichsobjekt eine andere Klasse ist
    @Test
    public void equals_ShouldReturnFalseWhenComparingWithDifferentClass() {
        assertFalse(userDetails.equals(new Object()));
    }
}
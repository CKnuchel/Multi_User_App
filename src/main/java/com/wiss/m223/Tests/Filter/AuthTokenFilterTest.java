package com.wiss.m223.Tests.Filter;

// Import-Anweisungen
import com.wiss.m223.DTO.JwtResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Diese Klasse testet die AuthTokenFilter-Klasse
public class AuthTokenFilterTest {

    // Instanz der JwtResponse-Klasse
    private JwtResponse jwtResponse;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die JwtResponse-Instanz
    @BeforeEach
    public void setUp() {
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        jwtResponse = new JwtResponse("testToken", 1L, "testUser", "testEmail", roles);
    }

    // Dieser Test überprüft die Methode getAccessToken der JwtResponse-Klasse
    @Test
    public void getAccessToken_ShouldReturnCorrectAccessToken() {
        assertEquals("testToken", jwtResponse.getAccessToken());
    }

    // Dieser Test überprüft die Methode getTokenType der JwtResponse-Klasse
    @Test
    public void getTokenType_ShouldReturnCorrectTokenType() {
        assertEquals("Bearer", jwtResponse.getTokenType());
    }

    // Dieser Test überprüft die Methode getId der JwtResponse-Klasse
    @Test
    public void getId_ShouldReturnCorrectId() {
        assertEquals(1L, jwtResponse.getId());
    }

    // Dieser Test überprüft die Methode getUsername der JwtResponse-Klasse
    @Test
    public void getUsername_ShouldReturnCorrectUsername() {
        assertEquals("testUser", jwtResponse.getUsername());
    }

    // Dieser Test überprüft die Methode getEmail der JwtResponse-Klasse
    @Test
    public void getEmail_ShouldReturnCorrectEmail() {
        assertEquals("testEmail", jwtResponse.getEmail());
    }

    // Dieser Test überprüft die Methode getRoles der JwtResponse-Klasse
    @Test
    public void getRoles_ShouldReturnCorrectRoles() {
        List<String> roles = jwtResponse.getRoles();
        assertEquals(2, roles.size());
        assertEquals("ROLE_USER", roles.get(0));
        assertEquals("ROLE_ADMIN", roles.get(1));
    }
}
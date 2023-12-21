package com.wiss.m223.Tests.Model;

import com.wiss.m223.Model.Role;
import com.wiss.m223.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Diese Klasse testet die User-Klasse
public class UserTest {

    // Instanz der User-Klasse
    private User user;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die User-Instanz
    @BeforeEach
    public void setUp() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Role.ERole.ROLE_USER));
        user = new User("testUser", "testEmail", "testPassword");
        user.setRoles(roles);
    }

    // Dieser Test überprüft die Methode getId der User-Klasse
    @Test
    public void getId_ShouldReturnCorrectId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    // Dieser Test überprüft die Methode getUsername der User-Klasse
    @Test
    public void getUsername_ShouldReturnCorrectUsername() {
        assertEquals("testUser", user.getUsername());
    }

    // Dieser Test überprüft die Methode getEmail der User-Klasse
    @Test
    public void getEmail_ShouldReturnCorrectEmail() {
        assertEquals("testEmail", user.getEmail());
    }

    // Dieser Test überprüft die Methode getPassword der User-Klasse
    @Test
    public void getPassword_ShouldReturnCorrectPassword() {
        assertEquals("testPassword", user.getPassword());
    }

    // Dieser Test überprüft die Methode getRoles der User-Klasse
    @Test
    public void getRoles_ShouldReturnCorrectRoles() {
        assertEquals(1, user.getRoles().size());
        assertEquals(Role.ERole.ROLE_USER, user.getRoles().iterator().next().getName());
    }

    // Dieser Test überprüft die Methode setId der User-Klasse
    @Test
    public void setId_ShouldSetIdCorrectly() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    // Dieser Test überprüft die Methode setId der User-Klasse
    @Test
    public void setId_ShouldNotMatchIncorrectId() {
        user.setId(2);
        assertNotEquals(1, user.getId());
    }

    // Dieser Test überprüft die Methode setUsername der User-Klasse
    @Test
    public void setUsername_ShouldSetUsernameCorrectly() {
        user.setUsername("newTestUser");
        assertEquals("newTestUser", user.getUsername());
    }

    // Dieser Test überprüft die Methode setUsername der User-Klasse
    @Test
    public void setUsername_ShouldNotMatchIncorrectUsername() {
        user.setUsername("newTestUser");
        assertNotEquals("testUser", user.getUsername());
    }

    // Dieser Test überprüft die Methode setEmail der User-Klasse
    @Test
    public void setEmail_ShouldSetEmailCorrectly() {
        user.setEmail("newTestEmail");
        assertEquals("newTestEmail", user.getEmail());
    }

    // Dieser Test überprüft die Methode setEmail der User-Klasse
    @Test
    public void setEmail_ShouldNotMatchIncorrectEmail() {
        user.setEmail("newTestEmail");
        assertNotEquals("testEmail", user.getEmail());
    }

    // Dieser Test überprüft die Methode setPassword der User-Klasse
    @Test
    public void setPassword_ShouldSetPasswordCorrectly() {
        user.setPassword("newTestPassword");
        assertEquals("newTestPassword", user.getPassword());
    }

    // Dieser Test überprüft die Methode setPassword der User-Klasse
    @Test
    public void setPassword_ShouldNotMatchIncorrectPassword() {
        user.setPassword("newTestPassword");
        assertNotEquals("testPassword", user.getPassword());
    }

    // Dieser Test überprüft die Methode setRoles der User-Klasse
    @Test
    public void setRoles_ShouldSetRolesCorrectly() {
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(new Role(Role.ERole.ROLE_ADMIN));
        user.setRoles(newRoles);
        assertEquals(1, user.getRoles().size());
        assertEquals(Role.ERole.ROLE_ADMIN, user.getRoles().iterator().next().getName());
    }

    // Dieser Test überprüft die Methode setRoles der User-Klasse
    @Test
    public void setRoles_ShouldNotMatchIncorrectRoles() {
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(new Role(Role.ERole.ROLE_ADMIN));
        user.setRoles(newRoles);
        assertNotEquals(Role.ERole.ROLE_USER, user.getRoles().iterator().next().getName());
    }
}
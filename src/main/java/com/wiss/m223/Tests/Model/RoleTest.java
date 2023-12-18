package com.wiss.m223.Tests.Model;

import com.wiss.m223.Model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Diese Klasse testet die Role-Klasse
public class RoleTest {

    // Instanz der Role-Klasse
    private Role role;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Role-Instanz
    @BeforeEach
    public void setUp() {
        role = new Role(Role.ERole.ROLE_USER);
    }

    // Dieser Test überprüft die Methode getId der Role-Klasse
    @Test
    public void getId_ShouldReturnCorrectId() {
        role.setId(1);
        assertEquals(1, role.getId());
    }

    // Dieser Test überprüft die Methode getName der Role-Klasse
    @Test
    public void getName_ShouldReturnCorrectName() {
        assertEquals(Role.ERole.ROLE_USER, role.getName());
    }

    // Dieser Test überprüft die Methode setId der Role-Klasse
    @Test
    public void setId_ShouldSetIdCorrectly() {
        role.setId(2);
        assertEquals(2, role.getId());
    }

    // Dieser Test überprüft die Methode setId der Role-Klasse
    @Test
    public void setId_ShouldNotMatchIncorrectId() {
        role.setId(2);
        assertNotEquals(1, role.getId());
    }

    // Dieser Test überprüft die Methode setName der Role-Klasse
    @Test
    public void setName_ShouldSetNameCorrectly() {
        role.setName(Role.ERole.ROLE_ADMIN);
        assertEquals(Role.ERole.ROLE_ADMIN, role.getName());
    }

    // Dieser Test überprüft die Methode setName der Role-Klasse
    @Test
    public void setName_ShouldNotMatchIncorrectName() {
        role.setName(Role.ERole.ROLE_ADMIN);
        assertNotEquals(Role.ERole.ROLE_USER, role.getName());
    }
}
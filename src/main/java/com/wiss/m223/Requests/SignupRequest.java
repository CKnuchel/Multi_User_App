package com.wiss.m223.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

// Diese Klasse repräsentiert die Anforderung zur Registrierung eines neuen Benutzers
public class SignupRequest {

    // Der Benutzername des Benutzers, der nicht leer sein darf und eine Länge zwischen 3 und 20 Zeichen haben muss
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    // Die E-Mail des Benutzers, die nicht leer sein darf, eine maximale Länge von 50 Zeichen haben muss und eine gültige E-Mail-Adresse sein muss
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    // Die Rolle(n) des Benutzers
    private Set<String> role;

    // Das Passwort des Benutzers, das nicht leer sein darf und eine Länge zwischen 6 und 40 Zeichen haben muss
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    // Getter-Methode für den Benutzernamen
    public String getUsername() {
        return username;
    }

    // Setter-Methode für den Benutzernamen
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter-Methode für die E-Mail
    public String getEmail() {
        return email;
    }

    // Setter-Methode für die E-Mail
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter-Methode für die Rolle(n)
    public Set<String> getRole() {
        return role;
    }

    // Setter-Methode für die Rolle(n)
    public void setRole(Set<String> role) {
        this.role = role;
    }

    // Getter-Methode für das Passwort
    public String getPassword() {
        return password;
    }

    // Setter-Methode für das Passwort
    public void setPassword(String password) {
        this.password = password;
    }
}
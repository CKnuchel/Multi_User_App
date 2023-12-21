package com.wiss.m223.Requests;

// Diese Klasse repräsentiert die Anforderung zum Einloggen eines Benutzers
public class LoginRequest {
    private String username; // Der Benutzername des Benutzers
    private String password; // Das Passwort des Benutzers

    // Getter-Methode für den Benutzernamen
    public String getUsername() {
        return username;
    }

    // Setter-Methode für den Benutzernamen
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter-Methode für das Passwort
    public String getPassword() {
        return password;
    }

    // Setter-Methode für das Passwort
    public void setPassword(String password) {
        this.password = password;
    }

    // Standardkonstruktor
    public LoginRequest() {
    }

    // Konstruktor mit Benutzername und Passwort als Parameter
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
package com.wiss.m223.DTO;

import java.util.List;

// Diese Klasse repräsentiert die JWT-Antwort, die nach einer erfolgreichen Authentifizierung zurückgegeben wird
public class JwtResponse {
    private String token; // Das JWT-Token
    private String type = "Bearer"; // Der Typ des Tokens, standardmäßig "Bearer"
    private Long id; // Die ID des authentifizierten Benutzers
    private String username; // Der Benutzername des authentifizierten Benutzers
    private String email; // Die E-Mail des authentifizierten Benutzers
    private List<String> roles; // Die Rollen des authentifizierten Benutzers

    // Konstruktor für die JwtResponse-Klasse
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    // Getter und Setter für die Felder der Klasse

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
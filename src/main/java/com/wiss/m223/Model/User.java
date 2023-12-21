package com.wiss.m223.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

// Definiert die Klasse als Entität, die in der Datenbank gespeichert werden kann
@Entity
// Legt den Namen der Tabelle in der Datenbank fest, die dieser Entität entspricht
@Table(name = "User")
public class User {
    // Definiert das Feld als Primärschlüssel der Entität
    @Id
    // Legt die Strategie für die Generierung des Primärschlüssels fest
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Stellt sicher, dass das Feld nicht null sein kann
    @NotBlank
    private String username;

    // Stellt sicher, dass das Feld nicht null sein kann
    @NotBlank
    private String email;

    // Stellt sicher, dass das Feld nicht null sein kann
    @NotBlank
    private String password;

    // Standardkonstruktor
    public User() {
    }

    // Konstruktor mit allen Feldern als Parameter
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
    }

    // Konstruktor mit Name, Email und Passwort als Parameter
    public User(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
    }

    // Definiert eine ManyToMany-Beziehung zu der Role-Entität
    @ManyToMany(fetch = FetchType.EAGER)
    // Legt die Tabelle und die Spalten für die Verbindung zwischen den Entitäten fest
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // Getter- und Setter-Methoden für die Felder der Klasse

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
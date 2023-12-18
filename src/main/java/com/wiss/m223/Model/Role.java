package com.wiss.m223.Model;

import jakarta.persistence.*;

// Definiert die Klasse als Entität, die in der Datenbank gespeichert werden kann
@Entity
// Legt den Namen der Tabelle in der Datenbank fest, die dieser Entität entspricht
@Table(name = "role")
public class Role {

    // Definiert das Feld als Primärschlüssel der Entität
    @Id
    // Legt die Strategie für die Generierung des Primärschlüssels fest
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Definiert das Feld als Enumerated, was bedeutet, dass es eine begrenzte Anzahl von möglichen Werten hat
    @Enumerated(EnumType.STRING)
    // Legt die Länge der Zeichenkette für dieses Feld fest
    @Column(length = 20)
    private ERole name;

    // Standardkonstruktor
    public Role() {
    }

    // Konstruktor mit dem Namen der Rolle als Parameter
    public Role(ERole name) {
        this.name = name;
    }

    // Getter- und Setter-Methoden für die Felder der Klasse

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    // Definiert die möglichen Werte für die Rolle
    public enum ERole {
        ROLE_USER, // Rolle für einen normalen Benutzer
        ROLE_MODERATOR, // Rolle für einen Moderator
        ROLE_ADMIN // Rolle für einen Administrator
    }
}
package com.wiss.m223.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

// Definiert die Klasse als Entität, die in der Datenbank gespeichert werden kann
@Entity
// Legt den Namen der Tabelle in der Datenbank fest, die dieser Entität entspricht
@Table(name = "Question")
public class Question {

    // Definiert das Feld als Primärschlüssel der Entität
    @Id
    // Legt die Strategie für die Generierung des Primärschlüssels fest
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    // Stellt sicher, dass das Feld nicht null sein kann
    @NotNull
    private String question;

    // Standardkonstruktor
    public Question() {
    }

    // Konstruktor mit der Frage als Parameter
    public Question(String question){
        this.question = question;
    }

    // Konstruktor mit allen Feldern als Parameter
    public Question(int id, String question) {
        this.questionId = id;
        this.question = question;
    }

    // Getter- und Setter-Methoden für die Felder der Klasse

    public int getId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.questionId = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
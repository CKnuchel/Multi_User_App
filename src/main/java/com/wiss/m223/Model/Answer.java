package com.wiss.m223.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

// Definiert die Klasse als Entität, die in der Datenbank gespeichert werden kann
@Entity
// Legt den Namen der Tabelle in der Datenbank fest, die dieser Entität entspricht
@Table(name = "Answer")
public class Answer {

    // Definiert das Feld als Primärschlüssel der Entität
    @Id
    // Legt die Strategie für die Generierung des Primärschlüssels fest
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;

    // Definiert eine ManyToOne-Beziehung zu der Question-Entität
    @ManyToOne(fetch = FetchType.EAGER)
    // Legt den Fremdschlüssel in der Datenbank fest
    @JoinColumn(name="questionId", nullable=false)
    private Question question;

    // Stellt sicher, dass das Feld nicht null sein kann
    @NotNull
    private String answer;

    // Standardkonstruktor
    public Answer() {
    }

    // Konstruktor mit Parametern
    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Konstruktor mit allen Feldern als Parameter
    public Answer(int answerId, Question question, String answer) {
        this.answerId = answerId;
        this.question = question;
        this.answer = answer;
    }

    // Getter- und Setter-Methoden für die Felder der Klasse

    public long getAnswerId() {
        return answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswerId() {this.answerId = answerId;}

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
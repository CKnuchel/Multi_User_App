package com.wiss.m223.Model;

import jakarta.persistence.*;

// Definiert die Klasse als Entität, die in der Datenbank gespeichert werden kann
@Entity
// Legt den Namen der Tabelle in der Datenbank fest, die dieser Entität entspricht
@Table(name = "Responses")
public class Responses {

    // Definiert das Feld als Primärschlüssel der Entität
    @Id
    // Legt die Strategie für die Generierung des Primärschlüssels fest
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int response_id;

    // Definiert eine ManyToOne-Beziehung zu der User-Entität
    @ManyToOne(fetch = FetchType.EAGER)
    // Legt den Fremdschlüssel in der Datenbank fest
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    // Definiert eine OneToOne-Beziehung zu der Answer-Entität
    @OneToOne(fetch = FetchType.EAGER)
    // Legt den Fremdschlüssel in der Datenbank fest
    @JoinColumn(name="answer_id", nullable=false)
    private Answer answer;

    // Standardkonstruktor
    public Responses() {
    }

    // Konstruktor mit User und Answer als Parameter
    public Responses(User user, Answer answer) {
        this.user = user;
        this.answer = answer;
    }

    // Konstruktor mit allen Feldern als Parameter
    public Responses(int response_id, User user, Answer answer) {
        this.response_id = response_id;
        this.user = user;
        this.answer = answer;
    }

    // Getter- und Setter-Methoden für die Felder der Klasse

    public int getResponse_id() {
        return response_id;
    }

    public User getUser() {
        return user;
    }

    public Answer getAnswers() {
        return answer;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnswers(Answer answers) {
        this.answer = answers;
    }

}
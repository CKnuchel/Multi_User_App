package com.wiss.m223.Tests.Model;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Diese Klasse testet die Question-Klasse
public class QuestionTest {

    // Instanzen der Answer- und Question-Klassen
    private Answer answer;
    private Question question;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Answer- und Question-Instanzen
    @BeforeEach
    public void setUp() {
        question = new Question();
        answer = new Answer(1, question, "testAnswer");
    }

    // Dieser Test überprüft die Methode setQuestion der Answer-Klasse
    @Test
    public void setQuestion_ShouldNotMatchIncorrectQuestion() {
        // Erstellt eine neue Question-Instanz
        Question newQuestion = new Question();
        // Setzt die Frage der Answer-Instanz auf die neue Frage
        answer.setQuestion(newQuestion);
        // Überprüft, ob die Frage der Answer-Instanz nicht der ursprünglichen Frage entspricht
        assertNotEquals(question, answer.getQuestion());
    }

    // Dieser Test überprüft die Methode setAnswer der Answer-Klasse
    @Test
    public void setAnswer_ShouldNotMatchIncorrectAnswer() {
        // Setzt die Antwort der Answer-Instanz auf eine neue Antwort
        answer.setAnswer("newTestAnswer");
        // Überprüft, ob die Antwort der Answer-Instanz nicht der ursprünglichen Antwort entspricht
        assertNotEquals("testAnswer", answer.getAnswer());
    }
}
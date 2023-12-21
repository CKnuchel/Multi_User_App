package com.wiss.m223.Tests.Model;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Diese Klasse testet die Answer-Klasse
public class AnswerTest {

    // Instanzen der Answer- und Question-Klassen
    private Answer answer;
    private Question question;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Answer- und Question-Instanzen
    @BeforeEach
    public void setUp() {
        question = new Question();
        answer = new Answer(1, question, "testAnswer");
    }

    // Dieser Test überprüft die Methode getAnswerId der Answer-Klasse
    @Test
    public void getAnswerId_ShouldReturnCorrectAnswerId() {
        assertEquals(1, answer.getAnswerId());
    }

    // Dieser Test überprüft die Methode getQuestion der Answer-Klasse
    @Test
    public void getQuestion_ShouldReturnCorrectQuestion() {
        assertEquals(question, answer.getQuestion());
    }

    // Dieser Test überprüft die Methode getAnswer der Answer-Klasse
    @Test
    public void getAnswer_ShouldReturnCorrectAnswer() {
        assertEquals("testAnswer", answer.getAnswer());
    }

    // Dieser Test überprüft die Methode setQuestion der Answer-Klasse
    @Test
    public void setQuestion_ShouldSetQuestionCorrectly() {
        Question newQuestion = new Question();
        answer.setQuestion(newQuestion);
        assertEquals(newQuestion, answer.getQuestion());
    }

    // Dieser Test überprüft die Methode setAnswer der Answer-Klasse
    @Test
    public void setAnswer_ShouldSetAnswerCorrectly() {
        answer.setAnswer("newTestAnswer");
        assertEquals("newTestAnswer", answer.getAnswer());
    }

    // Dieser Test überprüft den Konstruktor der Answer-Klasse
    @Test
    public void constructor_ShouldCreateAnswerWithCorrectAttributes() {
        Answer newAnswer = new Answer(3, question, "anotherTestAnswer");
        assertNotNull(newAnswer);
        assertEquals(3, newAnswer.getAnswerId());
        assertEquals(question, newAnswer.getQuestion());
        assertEquals("anotherTestAnswer", newAnswer.getAnswer());
    }
}
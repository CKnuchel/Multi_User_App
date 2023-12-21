package com.wiss.m223.Tests.Model;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Responses;
import com.wiss.m223.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Diese Klasse testet die Responses-Klasse
public class ResponsesTest {

    // Instanzen der Responses-, User- und Answer-Klassen
    private Responses responses;
    private User user;
    private Answer answer;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Responses-, User- und Answer-Instanzen
    @BeforeEach
    public void setUp() {
        user = new User();
        answer = new Answer();
        responses = new Responses(1, user, answer);
    }

    // Dieser Test überprüft die Methode getResponseId der Responses-Klasse
    @Test
    public void getResponseId_ShouldReturnCorrectResponseId() {
        assertEquals(1, responses.getResponse_id());
    }

    // Dieser Test überprüft die Methode getUser der Responses-Klasse
    @Test
    public void getUser_ShouldReturnCorrectUser() {
        assertEquals(user, responses.getUser());
    }

    // Dieser Test überprüft die Methode getAnswer der Responses-Klasse
    @Test
    public void getAnswer_ShouldReturnCorrectAnswer() {
        assertEquals(answer, responses.getAnswers());
    }

    // Dieser Test überprüft die Methode setResponseId der Responses-Klasse
    @Test
    public void setResponseId_ShouldSetResponseIdCorrectly() {
        responses.setResponse_id(2);
        assertEquals(2, responses.getResponse_id());
    }

    // Dieser Test überprüft die Methode setResponseId der Responses-Klasse
    @Test
    public void setResponseId_ShouldNotMatchIncorrectResponseId() {
        responses.setResponse_id(2);
        assertNotEquals(1, responses.getResponse_id());
    }

    // Dieser Test überprüft die Methode setUser der Responses-Klasse
    @Test
    public void setUser_ShouldSetUserCorrectly() {
        User newUser = new User();
        responses.setUser(newUser);
        assertEquals(newUser, responses.getUser());
    }

    // Dieser Test überprüft die Methode setUser der Responses-Klasse
    @Test
    public void setUser_ShouldNotMatchIncorrectUser() {
        User newUser = new User();
        responses.setUser(newUser);
        assertNotEquals(user, responses.getUser());
    }

    // Dieser Test überprüft die Methode setAnswer der Responses-Klasse
    @Test
    public void setAnswer_ShouldSetAnswerCorrectly() {
        Answer newAnswer = new Answer();
        responses.setAnswers(newAnswer);
        assertEquals(newAnswer, responses.getAnswers());
    }

    // Dieser Test überprüft die Methode setAnswer der Responses-Klasse
    @Test
    public void setAnswer_ShouldNotMatchIncorrectAnswer() {
        Answer newAnswer = new Answer();
        responses.setAnswers(newAnswer);
        assertNotEquals(answer, responses.getAnswers());
    }
}
package com.wiss.m223.Tests.Controller;

// Import-Anweisungen
import com.wiss.m223.Controller.QuestionController;
import com.wiss.m223.Model.Question;
import com.wiss.m223.Repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Diese Klasse testet die QuestionController-Klasse
public class QuestionControllerTest {

    // Erstellt eine Instanz der QuestionController-Klasse und injiziert die Abhängigkeiten
    @InjectMocks
    private QuestionController questionController;

    // Mock-Objekt für die benötigte Abhängigkeit
    @Mock
    private QuestionRepository questionRepository;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert das Mock-Objekt
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Dieser Test überprüft die Methode getQuestions der QuestionController-Klasse
    @Test
    public void getQuestions_ShouldReturnListOfQuestions() {
        // Legt das Verhalten des Mock-Objekts fest, wenn es aufgerufen wird
        when(questionRepository.findAll()).thenReturn(Arrays.asList(new Question(), new Question()));

        // Ruft die Methode getQuestions auf und speichert die Antwort
        ResponseEntity<List<Question>> response = questionController.getQuestions();

        // Überprüft, ob der Statuscode der Antwort OK ist und ob die Größe des Antwortkörpers 2 ist
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    // Dieser Test überprüft die Methode createQuestion der QuestionController-Klasse
    @Test
    public void createQuestion_ShouldReturnCreatedQuestion() {
        // Erstellt eine neue Question-Instanz
        Question question = new Question("testQuestion");

        // Legt das Verhalten des Mock-Objekts fest, wenn es aufgerufen wird
        when(questionRepository.save(any())).thenReturn(question);

        // Ruft die Methode createQuestion auf und speichert die Antwort
        ResponseEntity<Question> response = questionController.createQuestion(question);

        // Überprüft, ob der Statuscode der Antwort CREATED ist und ob der Antwortkörper der erstellten Frage entspricht
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(question, response.getBody());
    }

    // Dieser Test überprüft die Methode deleteQuestion der QuestionController-Klasse
    @Test
    public void deleteQuestion_ShouldReturnNoContentWhenQuestionExists() {
        // Legt das Verhalten des Mock-Objekts fest, wenn es aufgerufen wird
        when(questionRepository.findById(any())).thenReturn(Optional.of(new Question()));

        // Ruft die Methode deleteQuestion auf und speichert die Antwort
        ResponseEntity<HttpStatus> response = questionController.deleteQuestion(1);

        // Überprüft, ob der Statuscode der Antwort NO_CONTENT ist
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
package com.wiss.m223.Tests.Controller;

// Import-Anweisungen
import com.wiss.m223.Controller.ResponseController;
import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Responses;
import com.wiss.m223.Model.User;
import com.wiss.m223.Repository.AnswerRepository;
import com.wiss.m223.Repository.ResponseRepository;
import com.wiss.m223.Repository.UserRepository;
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

// Diese Klasse testet die ResponseController-Klasse
public class ResponseControllerTest {

    // Erstellt eine Instanz der ResponseController-Klasse und injiziert die Abhängigkeiten
    @InjectMocks
    private ResponseController responseController;

    // Mock-Objekte für die benötigten Abhängigkeiten
    @Mock
    private ResponseRepository responseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AnswerRepository answerRepository;

    // Diese Methode wird vor jedem Test ausgeführt und initialisiert die Mock-Objekte
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Dieser Test überprüft die Methode getAllResponses der ResponseController-Klasse
    @Test
    public void getAllResponses_ShouldReturnListOfResponses() {
        // Legt das Verhalten der Mock-Objekte fest, wenn sie aufgerufen werden
        when(responseRepository.findAll()).thenReturn(Arrays.asList(new Responses(), new Responses()));

        // Ruft die Methode getAllResponses auf und speichert die Antwort
        ResponseEntity<List<Responses>> response = responseController.getAllResponses();

        // Überprüft, ob der Statuscode der Antwort OK ist und ob die Größe des Antwortkörpers 2 ist
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    // Dieser Test überprüft die Methode createResponse der ResponseController-Klasse
    @Test
    public void createResponse_ShouldReturnNotFoundWhenUserOrAnswerDoesNotExist() {
        // Legt das Verhalten der Mock-Objekte fest, wenn sie aufgerufen werden
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        // Ruft die Methode createResponse auf und speichert die Antwort
        ResponseEntity<Responses> response = responseController.createResponse(1, 1);

        // Überprüft, ob der Statuscode der Antwort NOT_FOUND ist
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Dieser Test überprüft die Methode userAlreadyResponded der ResponseController-Klasse
    @Test
    public void userAlreadyResponded_ShouldReturnFalseWhenUserHasNotResponded() {
        // Legt das Verhalten der Mock-Objekte fest, wenn sie aufgerufen werden
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User()));
        when(answerRepository.findById(any())).thenReturn(Optional.of(new Answer()));
        when(responseRepository.findByUserId(any())).thenReturn(Arrays.asList());

        // Ruft die Methode userAlreadyResponded auf und speichert die Antwort
        ResponseEntity<Boolean> response = responseController.userAlreadyResponded("testUser", 1);

        // Überprüft, ob der Statuscode der Antwort OK ist und ob der Antwortkörper false ist
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody());
    }

    // Dieser Test überprüft die Methode userAlreadyResponded der ResponseController-Klasse
    @Test
    public void userAlreadyResponded_ShouldReturnNotFoundWhenUserOrAnswerDoesNotExist() {
        // Legt das Verhalten der Mock-Objekte fest, wenn sie aufgerufen werden
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // Ruft die Methode userAlreadyResponded auf und speichert die Antwort
        ResponseEntity<Boolean> response = responseController.userAlreadyResponded("testUser", 1);

        // Überprüft, ob der Statuscode der Antwort NOT_FOUND ist
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
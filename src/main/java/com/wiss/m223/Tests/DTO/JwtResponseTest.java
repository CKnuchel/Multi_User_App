package com.wiss.m223.Tests.DTO;

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

// Diese Klasse testet die JwtResponse
public class JwtResponseTest {

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
    public void createResponse_ShouldReturnCreatedResponseWhenUserAndAnswerExist() {
        // Legt das Verhalten der Mock-Objekte fest, wenn sie aufgerufen werden
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        when(answerRepository.findById(any())).thenReturn(Optional.of(new Answer()));
        when(responseRepository.save(any())).thenReturn(new Responses());

        // Ruft die Methode createResponse auf und speichert die Antwort
        ResponseEntity<Responses> response = responseController.createResponse(1, 1);

        // Überprüft, ob der Statuscode der Antwort CREATED ist und ob der Antwortkörper eine Instanz von Responses ist
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Responses.class, response.getBody().getClass());
    }
}
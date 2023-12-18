package com.wiss.m223.Controller;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Responses;
import com.wiss.m223.Model.User;
import com.wiss.m223.Repository.AnswerRepository;
import com.wiss.m223.Repository.ResponseRepository;
import com.wiss.m223.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ResponseController ist ein REST-Controller, der HTTP-Anfragen im Zusammenhang mit Antworten behandelt.
 * Er verwendet die CrossOrigin-Anmerkung, um die Freigabe von Ressourcen über Ursprünge hinweg zu ermöglichen.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    /**
     * Diese Methode behandelt GET-Anfragen, um alle Antworten abzurufen.
     * Sie gibt eine Liste aller Antworten zurück, wenn sie gefunden werden, sonst gibt sie einen NO_CONTENT-Status zurück.
     * Im Falle einer Ausnahme gibt sie einen INTERNAL_SERVER_ERROR-Status zurück.
     * @return ResponseEntity<List<Responses>> - eine Liste aller Antworten oder einen entsprechenden HTTP-Status.
     */
    @GetMapping("")
    public ResponseEntity<List<Responses>> getAllResponses() {
        try {
            List<Responses> responses = responseRepository.findAll();

            if (responses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Diese Methode behandelt POST-Anfragen zur Erstellung einer neuen Antwort.
     * Sie nimmt user_id und answer_id als Parameter, findet den entsprechenden User und Answer,
     * erstellt eine neue Antwort, speichert sie in der Datenbank und gibt sie zurück.
     * Wenn der User oder Answer nicht gefunden wird, gibt sie einen NOT_FOUND-Status zurück.
     * Im Falle einer Ausnahme gibt sie einen INTERNAL_SERVER_ERROR-Status zurück.
     * @param user_id - die ID des Benutzers.
     * @param answer_id - die ID der Antwort.
     * @return ResponseEntity<Responses> - die erstellte Antwort oder einen entsprechenden HTTP-Status.
     */
    @PostMapping("")
    public ResponseEntity<Responses> createResponse(@RequestParam int user_id, @RequestParam int answer_id) {
        try {
            Optional<User> user = userRepository.findById((long) user_id);
            Optional<Answer> answer = answerRepository.findById((long) answer_id);
            if (user.isPresent() && answer.isPresent()) {
                Responses response = new Responses(user.get(), answer.get());
                Responses _response = responseRepository.save(response);
                return new ResponseEntity<>(_response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Diese Methode behandelt POST-Anfragen, um zu überprüfen, ob ein Benutzer bereits auf eine Frage geantwortet hat.
     * Sie nimmt den Benutzernamen und die question_id als Parameter, findet den entsprechenden User und Answer,
     * überprüft, ob der Benutzer bereits auf die Frage geantwortet hat, und gibt einen booleschen Wert zurück.
     * Wenn der Benutzer oder die Antwort nicht gefunden wird, gibt sie einen NOT_FOUND-Status zurück.
     * @param username - der Benutzername des Benutzers.
     * @param question_id - die ID der Frage.
     * @return ResponseEntity<Boolean> - true, wenn der Benutzer bereits geantwortet hat, false sonst, oder einen entsprechenden HTTP-Status.
     */
    @PostMapping("/done")
    public ResponseEntity<Boolean> userAlreadyResponded(@RequestParam String username, @RequestParam int question_id)
    {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Answer> answer = answerRepository.findById((long) question_id);

        if (user.isPresent() && answer.isPresent()) {
            List<Responses> responses = responseRepository.findByUserId(user.get().getId());
            for (Responses response : responses) {
                if (response.getAnswers().getQuestion().getId() == question_id) {
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(false, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
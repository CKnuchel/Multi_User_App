package com.wiss.m223.Controller;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import com.wiss.m223.Repository.AnswerRepository;
import com.wiss.m223.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * AnswerController ist ein REST-Controller, der HTTP-Anfragen im Zusammenhang mit Antworten behandelt.
 * Er verwendet die CrossOrigin-Anmerkung, um die Freigabe von Ressourcen über Ursprünge hinweg zu ermöglichen.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * Diese Methode behandelt POST-Anfragen zur Erstellung einer neuen Antwort.
     * Sie nimmt eine Antwort und eine questionId als Parameter, findet die entsprechende Frage,
     * erstellt eine neue Antwort, speichert sie in der Datenbank und gibt sie zurück.
     * Wenn die Frage nicht gefunden wird, gibt sie einen NOT_FOUND-Status zurück.
     * @param answer - die Antwort.
     * @param questionId - die ID der Frage.
     * @return ResponseEntity<Answer> - die erstellte Antwort oder einen entsprechenden HTTP-Status.
     */
    @PostMapping("")
    public ResponseEntity<Answer> createAnswer(@RequestParam String answer, @RequestParam long questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            Answer _answer = answerRepository.save(new Answer(question.get(), answer));
            return new ResponseEntity<>(_answer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Diese Methode behandelt GET-Anfragen, um alle Antworten zu einer bestimmten Frage abzurufen.
     * Sie nimmt eine ID als Parameter, findet die entsprechende Frage,
     * ruft alle Antworten zu dieser Frage ab und gibt sie zurück.
     * Wenn die Frage nicht gefunden wird, gibt sie einen NOT_FOUND-Status zurück.
     * @param id - die ID der Frage.
     * @return ResponseEntity<List<Answer>> - eine Liste aller Antworten zu der Frage oder einen entsprechenden HTTP-Status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@PathVariable("id") long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            List<Answer> answers = answerRepository.findAllByQuestionId(id);
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
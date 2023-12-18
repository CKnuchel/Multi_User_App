package com.wiss.m223.Controller;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import com.wiss.m223.Repository.AnswerRepository;
import com.wiss.m223.Repository.QuestionRepository;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;


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
     * Alle Antworten zu einer Frage abfragen
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

package com.wiss.m223.Controller;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Responses;
import com.wiss.m223.Model.User;
import com.wiss.m223.Repository.AnswerRepository;
import com.wiss.m223.Repository.QuestionRepository;
import com.wiss.m223.Repository.ResponseRepository;
import com.wiss.m223.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("") // Unauthorized Error, aber wird trotzdem erstellt und gespeichert Problem mit Security
    public ResponseEntity<Responses> createResponse(@RequestParam long userId, @RequestParam long answerId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (user.isPresent() && answer.isPresent()) {
            Responses response = new Responses(user.get(), answer.get());
            responseRepository.save(response);
            return ResponseEntity.ok(response);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or Answer not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Responses>> getResponses() {
        List<Responses> responses = responseRepository.findAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responses> getResponseById(@PathVariable long id) {
        Optional<Responses> response = responseRepository.findById(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response not found");
        }
    }

}

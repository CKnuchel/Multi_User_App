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

}

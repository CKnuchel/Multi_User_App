package com.wiss.m223.Controller;

import com.wiss.m223.Model.Question;
import com.wiss.m223.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired // Nutzen Sie Autowiring, um die Abhängigkeit zu injizieren
    private QuestionRepository questionRepository;

    @GetMapping("")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

	@PostMapping("")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		Question _question = questionRepository.save(question);
		return new ResponseEntity<>(_question, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") int id) {
		try {
			questionRepository.deleteById((long) id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}

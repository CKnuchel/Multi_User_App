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

	@Autowired
	private AnswerController answerController;

	/**
	 * Abfragen aller Fragen
	 * */
    @GetMapping("")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

	/**
	 * Abfragen einer spezifischen Frage
	 * @param question
	 * @return
	 */
	@PostMapping("")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		Question _question = questionRepository.save(question);
		return new ResponseEntity<>(_question, HttpStatus.CREATED);
	}

	/**
	 * Löschen einer Frage
	 * @param id
	 * @return
	 */
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

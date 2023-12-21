package com.wiss.m223.Controller;

// Importiert die notwendigen Bibliotheken und Klassen
import com.wiss.m223.Model.Question;
import com.wiss.m223.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Erlaubt Cross-Origin-Anfragen von allen Ursprüngen und setzt das maximale Alter auf 3600 Sekunden
@CrossOrigin(origins = "*", maxAge = 3600)
// Definiert die Klasse als RestController, was bedeutet, dass sie HTTP-Anfragen behandeln kann
@RestController
// Legt den Pfad fest, unter dem dieser Controller erreichbar ist
@RequestMapping("/questions")
public class QuestionController {

    // Nutzen Sie Autowiring, um die Abhängigkeit zu injizieren
    @Autowired
    private QuestionRepository questionRepository;

    // Methode zum Abrufen aller Fragen
    @GetMapping("")
    public ResponseEntity<List<Question>> getQuestions() {
        // Ruft alle Fragen aus dem Repository ab
        List<Question> questions = questionRepository.findAll();
        // Gibt die Liste der Fragen und den HTTP-Status OK zurück
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Methode zum Erstellen einer neuen Frage
    @PostMapping("")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        // Speichert die neue Frage im Repository und gibt sie zurück
        Question _question = questionRepository.save(question);
        // Gibt die erstellte Frage und den HTTP-Status CREATED zurück
        return new ResponseEntity<>(_question, HttpStatus.CREATED);
    }

    // Methode zum Löschen einer spezifischen Frage
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") int id) {
        try {
            // Versucht, die Frage mit der gegebenen ID zu löschen
            questionRepository.deleteById((long) id);
            // Gibt den HTTP-Status NO_CONTENT zurück, wenn das Löschen erfolgreich war
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Gibt den HTTP-Status INTERNAL_SERVER_ERROR zurück, wenn beim Löschen ein Fehler aufgetreten ist
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
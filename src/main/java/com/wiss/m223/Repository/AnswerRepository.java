package com.wiss.m223.Repository;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Definiert das Interface als Repository, was bedeutet, dass es Operationen für den Zugriff auf die Datenbank bereitstellt
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // Methode zum Abrufen aller Antworten, die zu einer bestimmten Frage gehören
    // Der Parameter ist die ID der Frage
    List<Answer> findAllByQuestionId(long questionId);
}
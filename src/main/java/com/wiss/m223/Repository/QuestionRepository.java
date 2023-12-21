package com.wiss.m223.Repository;

import com.wiss.m223.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Definiert das Interface als Repository, was bedeutet, dass es Operationen für den Zugriff auf die Datenbank bereitstellt
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Spezifische Abfragen hier definieren

}
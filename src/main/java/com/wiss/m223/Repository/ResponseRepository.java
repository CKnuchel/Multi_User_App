package com.wiss.m223.Repository;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Responses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Definiert das Interface als Repository, was bedeutet, dass es Operationen für den Zugriff auf die Datenbank bereitstellt
@Repository
public interface ResponseRepository extends JpaRepository<Responses, Long> {

    // Methode zum Abrufen aller Antworten, die zu einem bestimmten Benutzer gehören
    // Der Parameter ist die ID des Benutzers
    List<Responses> findByUserId(Long userId);

}
package com.wiss.m223.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wiss.m223.Model.User;

// Definiert das Interface als Repository, was bedeutet, dass es Operationen für den Zugriff auf die Datenbank bereitstellt
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Methode zum Finden eines Benutzers anhand seines Benutzernamens
    // Der Parameter ist der Benutzername
    // Gibt ein Optional zurück, das den Benutzer enthält, wenn er gefunden wurde, und sonst leer ist
    Optional<User> findByUsername(String username);

    // Methode zum Überprüfen, ob ein Benutzer mit einem bestimmten Benutzernamen existiert
    // Der Parameter ist der Benutzername
    // Gibt true zurück, wenn ein Benutzer mit diesem Benutzernamen existiert, und false, wenn nicht
    Boolean existsByUsername(String username);

    // Methode zum Überprüfen, ob ein Benutzer mit einer bestimmten E-Mail existiert
    // Der Parameter ist die E-Mail
    // Gibt true zurück, wenn ein Benutzer mit dieser E-Mail existiert, und false, wenn nicht
    Boolean existsByEmail(String email);
}
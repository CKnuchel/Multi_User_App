package com.wiss.m223.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wiss.m223.Model.Role;
import com.wiss.m223.Model.Role.ERole;

// Definiert das Interface als Repository, was bedeutet, dass es Operationen für den Zugriff auf die Datenbank bereitstellt
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Methode zum Finden einer Rolle anhand ihres Namens
    // Der Parameter ist der Name der Rolle
    // Gibt ein Optional zurück, das die Rolle enthält, wenn sie gefunden wurde, und sonst leer ist
    Optional<Role> findByName(ERole name);
}
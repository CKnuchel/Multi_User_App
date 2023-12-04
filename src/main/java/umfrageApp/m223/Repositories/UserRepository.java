package umfrageApp.m223.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umfrageApp.m223.Model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

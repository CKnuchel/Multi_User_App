package com.wiss.m223.Repository;

import com.wiss.m223.Model.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {

    // Findet alle UserResponses für einen bestimmten User
    List<UserResponse> findByUserId(Long userId);

    // Findet alle UserResponses für eine bestimmte Antwort
    List<UserResponse> findByAnswerId(Long answerId);
}

package com.wiss.m223.Repository;

import com.wiss.m223.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findByAnswerId(Long answerId);

    Boolean existsByAnswerId(Long answerId);

    String findByAnserId(Long answerId);

}

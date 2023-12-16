package com.wiss.m223.Repository;

import com.wiss.m223.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByQuestionId(Long questionId);

    Boolean existsByQuestionId(Long questionId);

    Boolean existsByText(String text);
}

package com.wiss.m223.Repository;

import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionId(long questionId);
}

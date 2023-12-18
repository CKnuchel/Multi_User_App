package com.wiss.m223.Tests;
import com.wiss.m223.Model.Question;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void QuestionGetquestion() {
        // Arrange
        Question question = new Question("Ich bin ein Test");

        // Act
        String result = question.getQuestion();

        // Assert
        assert result.equals("Ich bin ein Test");
    }

}

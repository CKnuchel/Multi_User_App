package com.wiss.m223.Tests;
import com.wiss.m223.Model.Answer;
import com.wiss.m223.Model.Question;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    public void AnswerGetAnswer() {
        // Arrange
        Question question = new Question("Ich bin ein Test");
        Answer answer = new Answer(question, "Ich bin eine Antwort");

        // Act
        String result = answer.getAnswer();

        // Assert
        assert result.equals("Ich bin eine Antwort");
    }
}

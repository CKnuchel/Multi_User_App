package com.wiss.m223.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="questionId", nullable=false)
    private Question question;

    @NotNull
    private String answer;

    public Answer() {
    }

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Answer(int answerId, Question question, String answer) {
        this.answerId = answerId;
        this.question = question;
        this.answer = answer;
    }



    public long getAnswerId() {
        return answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswerId() {this.answerId = answerId;}

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

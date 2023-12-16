package com.wiss.m223.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @NotNull
    private String question;

    public Question() {
    }

    public Question(String question){
        this.question = question;
    }

    public Question(int id, String question) {
        this.questionId = id;
        this.question = question;
    }

    public int getId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.questionId = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}

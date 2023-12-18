package com.wiss.m223.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Responses")
public class Responses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int response_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="answer_id", nullable=false)
    private Answer answer;


    public Responses() {
    }

    public Responses(User user, Answer answer) {
        this.user = user;
        this.answer = answer;
    }

    public Responses(int response_id, User user, Answer answer) {
        this.response_id = response_id;
        this.user = user;
        this.answer = answer;
    }

    public int getResponse_id() {
        return response_id;
    }

    public User getUser() {
        return user;
    }

    public Answer getAnswers() {
        return answer;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnswers(Answer answers) {
        this.answer = answers;
    }

}
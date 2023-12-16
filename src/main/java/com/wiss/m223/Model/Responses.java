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
    @JoinColumn(name="id", nullable=false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "response_answer", joinColumns = @JoinColumn(name = "response_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<Answer> answers;

    private boolean isSelected;

    public Responses() {
    }

    public Responses(User user, Set<Answer> answers, boolean isSelected) {
        this.user = user;
        this.answers = answers;
        this.isSelected = isSelected;
    }

    public Responses(int response_id, User user, Set<Answer> answers, boolean isSelected) {
        this.response_id = response_id;
        this.user = user;
        this.answers = answers;
        this.isSelected = isSelected;
    }

    public int getResponse_id() {
        return response_id;
    }

    public User getUser() {
        return user;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
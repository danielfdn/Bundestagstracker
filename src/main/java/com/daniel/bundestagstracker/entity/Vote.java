package com.daniel.bundestagstracker.entity;

import jakarta.persistence.*;

@Table(name="vote")
@Entity
public class Vote {

    @Id
    private Long id;
    private String vote;

    @ManyToOne //
    @JoinColumn(name = "pollId") // Reference on Poll Table (FK)
    private Poll poll;

    @ManyToOne
    private Fraction fraction;

    public Vote() {}

    public Vote(Long id, String vote, Poll poll, Fraction fraction) {
        this.id = id;
        this.vote = vote;
        this.poll = poll;
        this.fraction = fraction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", vote='" + vote + '\'' +
                ", poll=" + poll +
                ", fraction=" + fraction +
                '}';
    }
}



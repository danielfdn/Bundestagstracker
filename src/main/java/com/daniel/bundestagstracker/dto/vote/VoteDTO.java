package com.daniel.bundestagstracker.dto.vote;
import com.daniel.bundestagstracker.dto.fraction.FractionDTO;

public class VoteDTO {
    private Long id;
    private String vote;
    private FractionDTO fraction;

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

    public FractionDTO getFraction() {
        return fraction;
    }

    public void setFraction(FractionDTO fraction) {
        this.fraction = fraction;
    }
}


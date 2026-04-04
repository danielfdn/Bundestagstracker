package com.daniel.bundestagstracker.dto.vote;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RelatedVoteData {

    @JsonProperty("votes")
    private List<VoteDTO> votes;

    public List<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteDTO> votes) {
        this.votes = votes;
    }
}


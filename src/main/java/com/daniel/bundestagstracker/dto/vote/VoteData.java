package com.daniel.bundestagstracker.dto.vote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteData {

    @JsonProperty("related_data")
    private RelatedVoteData relatedVoteData;

    public RelatedVoteData getRelatedVoteData() {
        return relatedVoteData;
    }

    public void setRelatedVoteData(RelatedVoteData relatedVoteData) {
        this.relatedVoteData = relatedVoteData;
    }
}

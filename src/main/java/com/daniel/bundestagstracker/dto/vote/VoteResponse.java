package com.daniel.bundestagstracker.dto.vote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteResponse {

    @JsonProperty("data")
    private VoteData data;

    public VoteData getData() {
        return data;
    }

    public void setData(VoteData data) {
        this.data = data;
    }
}


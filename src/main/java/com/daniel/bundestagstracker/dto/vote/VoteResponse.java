package com.daniel.bundestagstracker.dto.vote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VoteResponse {
        @JsonProperty("data")
        private VoteData voteData;

}


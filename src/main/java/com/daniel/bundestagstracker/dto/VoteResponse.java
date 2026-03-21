package com.daniel.bundestagstracker.dto;

import com.daniel.bundestagstracker.entity.Fraction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VoteResponse {
        @JsonProperty("data")
        private VoteData voteData;

}


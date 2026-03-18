package com.daniel.bundestagstracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VoteData {

    @JsonProperty("related_data")
    private RelatedVoteData relatedVoteData;

}

package com.daniel.bundestagstracker.dto.vote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RelatedVoteData {

    @JsonProperty("votes")
    private List<VoteDTO> votes;

    }

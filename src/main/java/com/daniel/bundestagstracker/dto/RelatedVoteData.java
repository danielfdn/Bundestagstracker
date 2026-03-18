package com.daniel.bundestagstracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class RelatedVoteData {

    private List<VoteDTO> votes;
    }

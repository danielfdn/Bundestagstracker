package com.daniel.bundestagstracker.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.AnyKeyJavaClass;

import java.util.Map;

@AllArgsConstructor
@Data
public class PollResult {

   private Long pollId;

    private int yesVotes;
    private int noVotes;
    private int noShowVotes;

    private Map<String, Integer> totalPartyYesVotes;
    private Map<String, Integer> totalPartyNoVotes;
    private Map<String, Integer> totalPartyNoShowVotes;


}

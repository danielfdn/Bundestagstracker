package com.daniel.bundestagstracker.dto.vote;
import com.daniel.bundestagstracker.dto.fraction.FractionDTO;
import lombok.Data;

@Data
public class VoteDTO {
    private Long id;
    private String vote;
    private FractionDTO fraction;

}


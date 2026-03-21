package com.daniel.bundestagstracker.dto;
import com.daniel.bundestagstracker.entity.Fraction;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class VoteDTO {
    private Long id;
    private String vote;
    private FractionDTO fraction;

}


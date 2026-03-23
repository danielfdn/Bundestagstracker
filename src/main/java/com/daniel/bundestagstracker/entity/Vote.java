package com.daniel.bundestagstracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vote {
    @Id
    private Long id;
    private String vote;

    @ManyToOne //
    @JoinColumn(name = "pollId") // Reference on Poll Table (FK)
    private Poll poll;

    @ManyToOne
    private Fraction fraction;

}

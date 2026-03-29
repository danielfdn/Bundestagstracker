package com.daniel.bundestagstracker.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity //Hibernate Annotation for PostgreSQL
public class Poll {

    @Id
    private Long id;
    private String label;
    private Boolean accepted;

    @JoinColumn(name="field_poll_date")
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    @JoinColumn(name="field_intro")
    private String info;
}

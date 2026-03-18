package com.daniel.bundestagstracker.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

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
    private Date date;

}

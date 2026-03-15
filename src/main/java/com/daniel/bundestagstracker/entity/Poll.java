package com.daniel.bundestagstracker.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;


@Entity //Hibernate Annotation for PostgreSQL
public class Poll {

    @Id
    private Long id;
    private String label;
    private Boolean accepted;
    private Date date;

    public Poll() {}

    public Poll(Long id, String label,Boolean accepted, Date date) {
        this.id = id;
        this.label = label;
        this.accepted = accepted;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date field_poll_date) {
        this.date = date;
    }
}

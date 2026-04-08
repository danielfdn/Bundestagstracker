package com.daniel.bundestagstracker.entity;


import jakarta.persistence.*;

import java.time.LocalDate;


@Table(name="poll")
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

    public Poll() {}

    public Poll(Long id, String label, Boolean accepted, LocalDate date, String info) {
        this.id = id;
        this.label = label;
        this.accepted = accepted;
        this.date = date;
        this.info = info;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", accepted=" + accepted +
                ", date=" + date +
                ", info='" + info + '\'' +
                '}';
    }
}



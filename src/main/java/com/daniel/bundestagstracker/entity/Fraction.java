package com.daniel.bundestagstracker.entity;

import jakarta.persistence.*;

@Table(name="fraction")
@Entity
public class Fraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true) //Datenbank lässt keine doppelte Speicherung zu
    String partyName;

    public Fraction() {}

    public Fraction(String partyName) {
        this.partyName = partyName;
    }

    public Fraction(Long id, String partyName) {
        this.id = id;
        this.partyName = partyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "id=" + id +
                ", partyName='" + partyName + '\'' +
                '}';
    }
}


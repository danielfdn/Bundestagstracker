package com.daniel.bundestagstracker.repository;

import com.daniel.bundestagstracker.entity.Fraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FractionRepo extends JpaRepository<Fraction, Long> {

    Optional<Fraction> findByPartyName(String name); //Spring automatically generates SQL Query

    List<Fraction> findAll();
}
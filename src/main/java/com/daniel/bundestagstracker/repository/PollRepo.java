package com.daniel.bundestagstracker.repository;
import com.daniel.bundestagstracker.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PollRepo extends JpaRepository<Poll, Long> { }

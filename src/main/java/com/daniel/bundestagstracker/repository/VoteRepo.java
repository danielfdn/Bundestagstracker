package com.daniel.bundestagstracker.repository;

import com.daniel.bundestagstracker.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> { //Spring Data JPA for defining repo-Interfaces & query-Methods

    List<Vote> id(Long id);

    List<Vote> findByPoll_Id(Long pollId);

    Long countByPoll_IdAndVote(Long pollId, String vote);
}

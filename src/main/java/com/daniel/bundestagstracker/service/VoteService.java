package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.entity.Vote;
import com.daniel.bundestagstracker.repository.VoteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepo voteRepo;

    public VoteService(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    public List<Vote> getVotes() {
        return voteRepo.findAll();
    }
}

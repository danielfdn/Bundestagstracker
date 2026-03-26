package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.entity.Poll;
import com.daniel.bundestagstracker.repository.PollRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PollService {

    private final PollRepo pollRepo;

    public PollService(PollRepo pollRepo) {
        this.pollRepo = pollRepo;
    }

    public List<Poll> getPolls() {
        return pollRepo.findAll();
        }

        public Poll getPollById(Long id) {
        return pollRepo.findById(id).orElse(null);
    }
}

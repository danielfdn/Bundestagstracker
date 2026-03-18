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

    // TODO: getPoll() method for single polls

    public List<Poll> getPolls() {
        return pollRepo.findAll();
        }
}

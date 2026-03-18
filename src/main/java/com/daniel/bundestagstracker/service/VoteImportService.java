package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.dto.VoteDTO;
import com.daniel.bundestagstracker.dto.VoteResponse;
import com.daniel.bundestagstracker.entity.Fraction;
import com.daniel.bundestagstracker.entity.Poll;
import com.daniel.bundestagstracker.entity.Vote;
import com.daniel.bundestagstracker.repository.PollRepo;
import com.daniel.bundestagstracker.repository.VoteRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service //Calls vote-API depending on poll_id and imports them into database
public class VoteImportService {
    private PollRepo pollRepo;
    private ApiService apiService;
    private VoteRepo voteRepo;

    private final String baseUrl = "https://www.abgeordnetenwatch.de/api/v2/polls/{id}?related_data=votes";

    public VoteImportService(PollRepo pollRepo, ApiService apiService, VoteRepo voteRepo) {
        this.pollRepo = pollRepo;
        this.apiService = apiService;
        this.voteRepo = voteRepo;
    }

    public void importVotes(Long pollId) {

        if(voteRepo.existsById(pollId)){
            return;
        }

           String url = baseUrl.replace("{id}", pollId.toString());

           VoteResponse response = apiService.fetch(url, VoteResponse.class);


            List<VoteDTO> dtos = response.getVoteData().getRelatedVoteData().getVotes();

            if(dtos == null || dtos.isEmpty()){
                return;
            }

            Poll poll = pollRepo.findById(pollId).orElseThrow();


            List<Vote> votes = dtos.stream() // -> Stream processes dtos List
                    .map(dto -> new Vote( // -> map maps every object of dto to vote-objects
                            dto.getId(),
                            dto.getVote(),
                            poll,
                            null)) //TODO: fraction api requests & database imports
                    .toList(); // -> remapped Object gets added to List votes

        voteRepo.saveAll(votes);
        }
    }

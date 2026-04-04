package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.dto.vote.VoteDTO;
import com.daniel.bundestagstracker.dto.vote.VoteResponse;
import com.daniel.bundestagstracker.entity.Fraction;
import com.daniel.bundestagstracker.entity.Poll;
import com.daniel.bundestagstracker.entity.Vote;
import com.daniel.bundestagstracker.repository.FractionRepo;
import com.daniel.bundestagstracker.repository.PollRepo;
import com.daniel.bundestagstracker.repository.VoteRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service //Calls vote-API depending on poll_id and imports them into database
public class VoteImportService {
    private final PollRepo pollRepo;
    private final ApiService apiService;
    private final VoteRepo voteRepo;
    private final FractionRepo fractionRepo;

    private final String baseUrl = "https://www.abgeordnetenwatch.de/api/v2/polls/{id}?related_data=votes";

    public VoteImportService(PollRepo pollRepo, ApiService apiService, VoteRepo voteRepo, FractionRepo fractionRepo) {
        this.pollRepo = pollRepo;
        this.apiService = apiService;
        this.voteRepo = voteRepo;
        this.fractionRepo = fractionRepo;
    }

    public void importVotes(Long pollId) {

        if(voteRepo.existsById(pollId)){
            return;
        }
           String url = baseUrl.replace("{id}", pollId.toString());
           VoteResponse response = apiService.fetch(url, VoteResponse.class);

           List<VoteDTO> voteDtos = response.getData().getRelatedVoteData().getVotes();

            if(voteDtos == null || voteDtos.isEmpty()){
                return;
            }

            Poll poll = pollRepo.findById(pollId).orElseThrow();

            List<Vote> votes = voteDtos.stream() // -> Stream processes dtos List
                    .map(voteDto -> new Vote( // -> map maps every object of dto to vote-objects
                            voteDto.getId(),
                            voteDto.getVote(),
                            poll,
                           getOrCreateFraction(voteDto.getFraction().getLabel())))//
                    .toList(); // -> remapped Object gets added to List votes

        voteRepo.saveAll(votes);
    }

    private Fraction getOrCreateFraction(String name) {

        if (name == null || name.isBlank()) {
            name = "UNKNOWN";
        }

        if (name.contains("(")) {
            name = name.substring(0, name.indexOf("("));
        }

        name = name.replaceAll("\\p{C}", "");

        name = name.trim().toUpperCase();

        final String normalizedName = name;

        return fractionRepo.findByPartyName(name)
                .orElseGet(() -> {
                    Fraction f = new Fraction();
                    f.setPartyName(normalizedName);
                    return fractionRepo.save(f);
                });
    }
}

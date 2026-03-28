package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.entity.Vote;
import com.daniel.bundestagstracker.repository.FractionRepo;
import com.daniel.bundestagstracker.repository.VoteRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollAnalysisService {
    private VoteRepo voteRepo;
    private FractionRepo fractionRepo;

    public PollAnalysisService(VoteRepo voteRepo, FractionRepo fractionRepo) {
        this.voteRepo = voteRepo;
        this.fractionRepo = fractionRepo;
    }

    public Map<String, Long> overallVoteResults(Long pollId) {

        Map<String, Long> result = new HashMap<>();

       result.put("yes", voteRepo.countByPoll_IdAndVote(pollId, "yes")); //Spring Data JPA automatically creates Queries
       result.put("no", voteRepo.countByPoll_IdAndVote(pollId, "no"));
       result.put("no_show", voteRepo.countByPoll_IdAndVote(pollId, "no_show"));

       return result;
    }

    public Map<String, Double> overallPercentageVoteResults(Long pollId) {
        long totalVotes = voteRepo.countByPoll_Id(pollId);

        if(totalVotes == 0){
            return Map.of();
        }

        Map<String, Long> results = overallVoteResults(pollId);
        Map<String, Double> percentageResults = results.entrySet().stream()
                .collect(Collectors.toMap(
                entry -> entry.getKey(),
                entry -> Math.round(((double)entry.getValue() / totalVotes * 100) * 100) / 100.0));
        return percentageResults;
    }

    public Map<String, Map<String, Long>> detailedVoteResults(Long pollId) {

        Map<String, Map<String, Long>> detailedResult = new HashMap<>();

       detailedResult.put("yes", new HashMap<>());
       detailedResult.put("no", new HashMap<>());
       detailedResult.put("no_show", new HashMap<>());

        List<Vote> votes = voteRepo.findByPoll_Id(pollId);

        for(Vote vote : votes) {
            String voteType = vote.getVote();
            String partyName = vote.getFraction().getPartyName();

            Map<String, Long> voteMap = detailedResult.get(voteType);
                if(voteMap == null) continue;

            voteMap.put(partyName,
                    voteMap.getOrDefault(partyName, 0L) + 1);
        }

        return detailedResult;
    }

}


package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.entity.Vote;
import com.daniel.bundestagstracker.repository.VoteRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {
    private final VoteRepo voteRepo;

    public VoteService(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    public Map<String, Long> overallVoteResults(Long pollId) {

        Map<String, Long> result = new HashMap<>();

       result.put("yes", voteRepo.countByPoll_IdAndVote(pollId, "yes")); //Spring Data JPA automatically creates Queries
       result.put("no", voteRepo.countByPoll_IdAndVote(pollId, "no"));
       result.put("no_show", voteRepo.countByPoll_IdAndVote(pollId, "no_show"));

       return result;
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


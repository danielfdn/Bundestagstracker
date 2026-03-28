package com.daniel.bundestagstracker.controller;

import com.daniel.bundestagstracker.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PollController {

    private final PollService pollService;
    private final PollImportService pollImportService;
    private final VoteImportService voteImportService;
    private final VoteService voteService;
    private final PollAnalysisService pollAnalysisService;

   public PollController(PollService pollService, PollImportService pollImportService, VoteImportService voteImportService, VoteService voteService, PollAnalysisService pollAnalysisService)
   {
        this.pollService = pollService;
        this.pollImportService = pollImportService;
        this.voteImportService = voteImportService;
        this.voteService = voteService;
        this.pollAnalysisService = pollAnalysisService;
   }

@GetMapping("/polls")
    public String testing(Model model) {
       pollImportService.importPolls();
    model.addAttribute("polls", pollService.getPolls());
       return "homepage";
    }

    @GetMapping("/polls/{id}")
    public String testing2(Model model, @PathVariable Long id) { //TODO: cut out testing endpoint due to unnecessary redundance
       voteImportService.importVotes(id);
       model.addAttribute("votes", voteService.getVotesByPollId(id));
       return "impressum";
    }

    @GetMapping("/polls2/{id}")
    public String testing3(Model model, @PathVariable Long id) { //TODO: cut out testing endpoint due to unnecessary redundance
       model.addAttribute("overallVotes", pollAnalysisService.overallVoteResults(id));
       model.addAttribute("detailedVotes", pollAnalysisService.detailedVoteResults(id));
       return "impressum2";
   }

   @GetMapping("/chart/{id}")
    public String testing4(Model model, @PathVariable Long id) {
       voteImportService.importVotes(id);
       model.addAttribute("poll", pollService.getPollById(id));
       model.addAttribute("overallVotes",  pollAnalysisService.overallVoteResults(id));
       model.addAttribute("overallPercentageVotes", pollAnalysisService.overallPercentageVoteResults(id));

       model.addAttribute("detailedVotes", pollAnalysisService.detailedVoteResults(id));
       return "testing/index";
   }


}

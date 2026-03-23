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
    public String testing2(Model model, @PathVariable Long id) {
       voteImportService.importVotes(id);
       model.addAttribute("votes", voteService.getVotes());
       return "impressum";
    }

    @GetMapping("/polls2/{id}")
    public String testing3(Model model, @PathVariable Long id) {
       model.addAttribute("detailedVotes",pollAnalysisService.detailedVoteResults(id));
       return "impressum2"; //TODO: figure out how to implement map with thymeleaf in html file
   }


}

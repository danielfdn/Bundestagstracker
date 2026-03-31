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
    private final PollAnalysisService pollAnalysisService;

   public PollController(PollService pollService, PollImportService pollImportService, VoteImportService voteImportService, PollAnalysisService pollAnalysisService)
   {
        this.pollService = pollService;
        this.pollImportService = pollImportService;
        this.voteImportService = voteImportService;
        this.pollAnalysisService = pollAnalysisService;
   }

   @GetMapping("bundestagstracker/chart/{id}")
    public String charts(Model model, @PathVariable Long id) {
       voteImportService.importVotes(id);
       model.addAttribute("poll", pollService.getPollById(id));
       model.addAttribute("overallVotes",  pollAnalysisService.overallVoteResults(id));

       model.addAttribute("detailedVotes", pollAnalysisService.detailedVoteResults(id));
       return "finishedTemplates/charts";
   }

   @GetMapping("/bundestagstracker")
    public String homepage(Model model) {
       pollImportService.importPolls();
    model.addAttribute("polls", pollService.getPolls());
       return "finishedTemplates/bundestagstracker";
   }

   @GetMapping("/bundestagstracker/info")
    public String info() {
       return "finishedTemplates/info";
   }
}

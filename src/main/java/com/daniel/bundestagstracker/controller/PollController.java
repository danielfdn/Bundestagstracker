package com.daniel.bundestagstracker.controller;

import com.daniel.bundestagstracker.service.PollImportService;
import com.daniel.bundestagstracker.service.PollService;
import com.daniel.bundestagstracker.service.VoteImportService;
import com.daniel.bundestagstracker.service.VoteService;
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

   public PollController(PollService pollService, PollImportService pollImportService, VoteImportService voteImportService, VoteService voteService) {
        this.pollService = pollService;
        this.pollImportService = pollImportService;
        this.voteImportService = voteImportService;
        this.voteService = voteService;
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
}

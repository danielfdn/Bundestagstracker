package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.dto.PollDTO;
import com.daniel.bundestagstracker.dto.PollResponse;
import com.daniel.bundestagstracker.entity.Poll;
import com.daniel.bundestagstracker.repository.PollRepo;
import org.springframework.stereotype.Service;

@Service //calls poll API and imports them into database
public class PollImportService {

    private final ApiService apiService; //sends get Requests to external API's
    private final PollRepo pollRepo;
    private final String url = "https://www.abgeordnetenwatch.de/api/v2/polls?field_legislature[entity.id]=161";

    public PollImportService(PollRepo pollRepo, ApiService apiService) { //Injecting Beans through constructor injection
        this.pollRepo = pollRepo;
        this.apiService = apiService;
    }

    public void importPolls() { //Mapping DTO to Entity

        PollResponse response = apiService.fetch(url, PollResponse.class); //API calling via restTemplate - saving JSON in Java Objects
            for(PollDTO dto : response.getData()) {
                Poll poll = new Poll(
                        dto.getId(),
                        dto.getLabel(),
                        dto.getField_accepted(),
                        dto.getDate()
                );
                pollRepo.save(poll); //saving objects to DB
            }
    }
}

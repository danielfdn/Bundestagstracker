package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.dto.PollDTO;
import com.daniel.bundestagstracker.entity.Poll;
import com.daniel.bundestagstracker.repository.PollRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PollImportService {

    private final RestTemplate restTemplate;
    private final PollRepo pollRepo;
    private final String url = "https://www.abgeordnetenwatch.de/api/v2/polls?field_legislature[entity.id]=161";


    public PollImportService(RestTemplate restTemplate, PollRepo pollRepo) { //Injecting Beans through constructor injection
        this.restTemplate = restTemplate;
        this.pollRepo = pollRepo;
    }

    public void importPolls() { //Mapping DTO to Entity

        PollResponse response = restTemplate.getForObject(url, PollResponse.class); //API calling - saving JSON in Java Objects
            for(PollDTO dto : response.getData()) {
                Poll poll = new Poll(
                        dto.getId(),
                        dto.getLabel(),
                        dto.isField_accepted(),
                        dto.getDate()
                );
                pollRepo.save(poll); //saving objects to DB
            }
    }
    @PostConstruct
    public void init() {
        importPolls();
    }
}

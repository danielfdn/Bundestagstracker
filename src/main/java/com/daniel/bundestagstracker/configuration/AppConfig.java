package com.daniel.bundestagstracker.configuration;

import com.daniel.bundestagstracker.dto.PollDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public PollDTO getPollDto() {
        return new PollDTO();
    }

}

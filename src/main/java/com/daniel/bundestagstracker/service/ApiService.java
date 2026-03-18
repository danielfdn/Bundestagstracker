package com.daniel.bundestagstracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T fetch(String url, Class<T> type) {
        return restTemplate.getForObject(url, type);
    }

}

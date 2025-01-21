package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.DateTimeRestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorldDateService {

    @Autowired
    private final RestTemplate restTemplate;


    public WorldDateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DateTimeRestAPI makeRequest(String url) {
        return restTemplate.exchange(url, HttpMethod.GET,null, DateTimeRestAPI.class).getBody();
    }
}


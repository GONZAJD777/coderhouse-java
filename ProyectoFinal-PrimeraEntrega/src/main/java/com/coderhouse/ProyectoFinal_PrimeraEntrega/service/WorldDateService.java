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

    private final String UTC_DATE_URL = "http://worldclockapi.com/api/json/utc/now";


    public WorldDateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DateTimeRestAPI makeRequest() {
        return restTemplate.exchange(UTC_DATE_URL, HttpMethod.GET,null, DateTimeRestAPI.class).getBody();
    }
}


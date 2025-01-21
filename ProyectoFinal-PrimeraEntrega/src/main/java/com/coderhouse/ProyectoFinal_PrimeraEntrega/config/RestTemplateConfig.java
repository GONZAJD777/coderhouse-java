package com.coderhouse.ProyectoFinal_PrimeraEntrega.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        SimpleClientHttpRequestFactory mRequestFactory = new SimpleClientHttpRequestFactory();
        mRequestFactory.setConnectTimeout(10000);
        mRequestFactory.setReadTimeout(10000);

        return builder.requestFactory(() -> mRequestFactory).build();
    }
}
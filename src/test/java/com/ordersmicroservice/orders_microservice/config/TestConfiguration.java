package com.ordersmicroservice.orders_microservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

@org.springframework.boot.test.context.TestConfiguration
@Profile("test")
public class TestConfiguration {

        @Bean
        public RestClient.Builder restClientBuilder(){
            return RestClient.builder().baseUrl("http://localhost:8081");
        }
    }

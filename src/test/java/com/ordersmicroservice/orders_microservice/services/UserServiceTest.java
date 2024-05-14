package com.ordersmicroservice.orders_microservice.services;

import com.ordersmicroservice.orders_microservice.dto.UserDto;
import com.ordersmicroservice.orders_microservice.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;

import java.io.IOException;

public class UserServiceTest {
    private MockWebServer mockWebServer;
    private UserServiceImpl userServiceImpl;
    private String userJson;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();
        userServiceImpl = new UserServiceImpl(webClient);
    }

    @Test
    @DisplayName("When fetching a user by ID, " +
            "then the correct user details are returned")
    void testGetUserById() {
        String userJson = """
                {
                    "id": 100,
                    "email": "john.doe@example.com",
                    "name": "John",
                    "lastName": "Doe",
                    "password": "password123",
                    "fidelityPoints": 1000,
                    "birthDate": "1985-10-15",
                    "phone": "1234567890",
                    "country": {
                        "name": "USA",
                        "code": "US",
                        "tax": "21.0"
                    }
                }
                """;
        mockWebServer.enqueue(new MockResponse()
                .setBody(userJson)
                .addHeader("Content-Type", "application/json"));

        Mono<UserDto> userMono = userServiceImpl.getUserById(100L);

        StepVerifier.create(userMono)
                .expectNextMatches(user ->
                        user.getId().equals(100L) &&
                                user.getPhone().equals("1234567890"))
                .verifyComplete();

    }

    @Test
    @DisplayName("When fetching a non-existent user by ID, then a 404 error is returned")
    void testGetUserByIdNotFound() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody("User not found")
                .addHeader("Content-Type", "text/plain"));
        Mono<UserDto> userMono = userServiceImpl.getUserById(999L);
        StepVerifier.create(userMono)
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException &&
                                ((WebClientResponseException) throwable).getStatusCode() == HttpStatus.NOT_FOUND)
                .verify();
    }

    @Test
    @DisplayName("When fetching a User by ID and an internal server error occurs, then a 500 error is returned")
    void testGetProductByIdServerError() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error")
                .addHeader("Content-Type", "text/plain"));
        Mono<UserDto> userMono = userServiceImpl.getUserById(1L);

        StepVerifier.create(userMono)
                .expectErrorMatches(throwable ->
                        throwable instanceof WebClientResponseException &&
                                ((WebClientResponseException) throwable).getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                .verify();
    }


    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

}

package com.linkexperts.userjsonapi;

import com.linkexperts.userjsonapi.domain.model.User;
import com.linkexperts.userjsonapi.util.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        webClient = webClientBuilder.build();
    }

    @Test
    void testGetUsers() {
        Mono<List<User>> usersMono = webClient.get()
                .uri(Constantes.BASE_URL + Constantes.USERS)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {});

        StepVerifier.create(usersMono)
                .expectNextMatches(users -> users.size() > 0)
                .verifyComplete();
    }
}
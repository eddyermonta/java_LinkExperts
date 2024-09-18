package com.linkexperts.userjsonapi;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import com.linkexperts.userjsonapi.presentation.controller.impl.UserController;
import com.linkexperts.userjsonapi.services.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private IUserService userService;

    @Test
    void testGetUserById() {
        UserDTO userDTO = new UserDTO("John Doe", "johndoe", "john@example.com", "123-456-7890", "johndoe.com");

        when(userService.getUserById("1")).thenReturn(Mono.just(userDTO));

        webTestClient.get().uri("/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDTO.class).isEqualTo(userDTO);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userService.getUserById("2")).thenReturn(Mono.empty());

        webTestClient.get().uri("/users/2")
                .exchange()
                .expectStatus().isNotFound();
    }
}
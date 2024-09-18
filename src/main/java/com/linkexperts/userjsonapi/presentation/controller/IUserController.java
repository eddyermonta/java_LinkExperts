package com.linkexperts.userjsonapi.presentation.controller;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserController {
    Flux<UserDTO> getUsers();
    Mono<ResponseEntity<UserDTO>> getUserById(String id);
}

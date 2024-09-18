package com.linkexperts.userjsonapi.services;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Flux<UserDTO> getUsers();
    Mono<UserDTO> getUserById(String id);
}

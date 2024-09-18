package com.linkexperts.userjsonapi.presentation.controller;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import reactor.core.publisher.Flux;

public interface IUserController {
    Flux<UserDTO> getUsers();
}

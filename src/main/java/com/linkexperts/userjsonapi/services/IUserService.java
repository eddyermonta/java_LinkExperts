package com.linkexperts.userjsonapi.services;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import reactor.core.publisher.Flux;

public interface IUserService {
    Flux<UserDTO> getUsers();

}

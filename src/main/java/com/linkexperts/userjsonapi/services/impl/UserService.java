package com.linkexperts.userjsonapi.services.impl;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import com.linkexperts.userjsonapi.domain.model.User;
import com.linkexperts.userjsonapi.persistence.mapper.UserMapper;
import com.linkexperts.userjsonapi.services.IUserService;
import com.linkexperts.userjsonapi.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class UserService implements IUserService {

    private final WebClient webClient;
    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Constantes.BASE_URL).build();
    }


    @Override
    public Flux<UserDTO> getUsers() {
        return  webClient.get()
                .uri(Constantes.USERS)
                .retrieve()
                .bodyToFlux(User.class)
                .map(userMapper::toDTO);
    }
}

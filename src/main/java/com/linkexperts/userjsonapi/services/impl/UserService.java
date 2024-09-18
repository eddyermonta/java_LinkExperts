package com.linkexperts.userjsonapi.services.impl;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import com.linkexperts.userjsonapi.domain.exceptions.WebClientErrorHandler;
import com.linkexperts.userjsonapi.domain.model.User;
import com.linkexperts.userjsonapi.persistence.mapper.UserMapper;
import com.linkexperts.userjsonapi.services.IUserService;
import com.linkexperts.userjsonapi.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    private final WebClient webClient;
    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Constantes.BASE_URL).build();
    }

    @Cacheable(value = "users", cacheManager = "cacheManager")
    @Override
    public Flux<UserDTO> getUsers() {
        return webClient.get().
                uri(Constantes.USERS).
                retrieve().
                onStatus(HttpStatusCode::isError, WebClientErrorHandler::handleHttpError)
                .bodyToFlux(User.class)
                .map(userMapper::toDTO)
                .onErrorResume(e -> Flux.error(new RuntimeException(Constantes.ERROR_FETCHING_USERS + e.getMessage())));
    }

    @Cacheable(value = "users", key = "#id", cacheManager = "cacheManager")
    @Override
    public Mono<UserDTO> getUserById(String id) {
        return webClient.get()
                .uri(Constantes.USERS+Constantes.ID, id)
                .retrieve()
                .onStatus(HttpStatusCode::isError, WebClientErrorHandler::handleHttpError)
                .bodyToMono(User.class)
                .map(userMapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException(Constantes.ERROR_FETCHING_USERS + e.getMessage())));
    }
}

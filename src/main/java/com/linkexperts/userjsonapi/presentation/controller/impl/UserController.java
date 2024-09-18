package com.linkexperts.userjsonapi.presentation.controller.impl;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import com.linkexperts.userjsonapi.presentation.controller.IUserController;
import com.linkexperts.userjsonapi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDTO>> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)  // Mapea el UserDTO a ResponseEntity<UserDTO>
                .defaultIfEmpty(ResponseEntity.notFound().build())  // Devuelve 404 si no se encuentra el usuario
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));  // Devuelve 500 en caso de error
    }
}

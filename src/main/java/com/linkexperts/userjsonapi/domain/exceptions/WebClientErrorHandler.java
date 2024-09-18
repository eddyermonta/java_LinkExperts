package com.linkexperts.userjsonapi.domain.exceptions;

import com.linkexperts.userjsonapi.util.Constantes;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public class WebClientErrorHandler {

    //errores para Mono y Flux
    public static Mono<Throwable> handleHttpError(ClientResponse response) {
        HttpStatusCode statusCode = response.statusCode();

        if (statusCode.is4xxClientError()) {
            return Mono.error(new ResourceNotFoundException(Constantes.USER_NOT_FOUND));
        } else if (statusCode.is5xxServerError()) {
            return Mono.error(new RuntimeException(Constantes.INTERNAL_SERVER_ERROR));
        } else {
            return Mono.error(new RuntimeException(Constantes.AN_UNEXPECTED_ERROR_OCURRED));
        }
    }
}

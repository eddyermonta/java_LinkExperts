package com.linkexperts.userjsonapi.config;

import com.linkexperts.userjsonapi.util.Constantes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient userClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(Constantes.BASE_URL).build();
    }
}


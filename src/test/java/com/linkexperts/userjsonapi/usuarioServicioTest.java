package com.linkexperts.userjsonapi;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import com.linkexperts.userjsonapi.domain.model.User;
import com.linkexperts.userjsonapi.persistence.mapper.UserMapper;
import com.linkexperts.userjsonapi.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    void testGetUserById_Success() {
        User user = new User(1L, "John Doe", "johndoe", "john@example.com", "123-456-7890", "johndoe.com");
        UserDTO userDTO = new UserDTO("John Doe", "johndoe", "john@example.com", "123-456-7890", "johndoe.com");

        when(responseSpec.bodyToMono(User.class)).thenReturn(Mono.just(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        Mono<UserDTO> result = userService.getUserById("1");

        assertEquals("UserDTO should be mapped correctly", userDTO, result.block());
    }

    @Test
    void testGetUserById_Error() {
        when(responseSpec.bodyToMono(User.class)).thenReturn(Mono.error(new RuntimeException("Error")));

        Mono<UserDTO> result = userService.getUserById("1");

        try {
            result.block(); // This will throw an exception
            fail("Exception should have been thrown");
        } catch (RuntimeException e) {
            assertEquals("Error fetching user", e.getMessage());
        }
    }
}

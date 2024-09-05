package com.nturbo1.api_gateway.service;

import com.nturbo1.api_gateway.dto.CustomUserDetails;
import com.nturbo1.api_gateway.dto.UserDto;
import com.nturbo1.api_gateway.exception.util.ExceptionMessage;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {

    @Value("${APP_SERVER_HOST}")
    private String appServerHost;
    @Value("${USER_SERVICE_PORT}")
    private String userServicePort;

    private final WebClient webClient = WebClient.builder().build();

    @Override
    public Mono<UserDetails> findByUsername(String username) throws UsernameNotFoundException {
        log.info("findByUsername ------ Username: {}", username);
        return getUserByEmail(username);
    }

    private Mono<UserDetails> getUserByEmail(String email) {
        return webClient
                .get()
                .uri(createGetUserByEmailUri(email))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(), email)))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getMessage()))
                )
                .bodyToMono(UserDto.class)
                .map(CustomUserDetails::new);
    }

    private URI createGetUserByEmailUri(String email) {
        return UriComponentsBuilder
                .fromUriString("http://"+ appServerHost + ":" + userServicePort + "/api/v1/users/auth")
                .queryParam("email", email)
                .build()
                .toUri();
    }
}

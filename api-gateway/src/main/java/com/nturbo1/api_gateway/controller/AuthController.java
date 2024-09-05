package com.nturbo1.api_gateway.controller;

import com.nturbo1.api_gateway.exception.util.ExceptionMessage;
import com.nturbo1.api_gateway.request.AuthRequest;
import com.nturbo1.api_gateway.request.RegisterUserRequest;
import com.nturbo1.api_gateway.response.UserResponse;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final ServerSecurityContextRepository securityContextRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${APP_SERVER_HOST}")
    private String appServerHost;
    @Value("${USER_SERVICE_PORT}")
    private String userServicePort;

    private static final WebClient webClient = WebClient.builder().build();

    @PostMapping("/auth/login")
    public Mono<ResponseEntity<Object>> login(ServerWebExchange exchange, @RequestBody AuthRequest authRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

        return reactiveAuthenticationManager.authenticate(authentication)
                .flatMap(auth -> {
                    SecurityContext securityContext = new SecurityContextImpl(auth);

                    return securityContextRepository.save(exchange, securityContext)
                            .then(Mono.just(ResponseEntity.ok().build()));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/auth/register")
    public Mono<ResponseEntity<UserResponse>> register(@RequestBody RegisterUserRequest userRequest) {
        log.debug("AuthController.register(RegisterUserRequest userRequest) ------- RegisterUserRequest: {})", userRequest);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        log.debug("AuthController.register(RegisterUserRequest userRequest) ------- RegisterUserRequest (after password encoding): {}", userRequest);

        return webClient.post()
                .uri(createAddUserUri())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(userRequest)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getMessage()))
                )
                .toEntity(UserResponse.class);
    }

    private URI createAddUserUri() {
        return UriComponentsBuilder
                .fromUriString("http://"+ appServerHost + ":" + userServicePort + "/api/v1/users")
                .build()
                .toUri();
    }
}

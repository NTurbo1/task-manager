package com.nturbo1.api_gateway.controller;

import com.nturbo1.api_gateway.request.AuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final ServerSecurityContextRepository securityContextRepository;

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
}

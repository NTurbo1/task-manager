package com.nturbo1.api_gateway.config.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUri {

    public static final Set<String> PUBLIC_URIS = Set.of(
            // Auth endpoints
            "/api/v1/auth/login", "/api/v1/auth/register",
            // Swagger/OpenApi endpoints
            "/swagger-ui.html", "/swagger-ui/**", "/openapi/**", "/webjars/**", "/v3/api-docs/**",
            "/user-service/v3/api-docs/**"
    );
}

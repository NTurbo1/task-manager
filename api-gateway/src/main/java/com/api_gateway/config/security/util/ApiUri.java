package com.api_gateway.config.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUri {

    public static final Set<String> PUBLIC_URIS = Set.of(
            "/api/v1/login", "/api/v1/register"
    );
}

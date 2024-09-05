package com.nturbo1.api_gateway.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}

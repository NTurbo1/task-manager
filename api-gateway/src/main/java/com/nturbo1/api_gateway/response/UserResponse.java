package com.nturbo1.api_gateway.response;

public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}

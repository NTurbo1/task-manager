package com.nturbo1.auth_service.response;

import org.springframework.http.HttpStatus;

public record CreateKeycloakUserResponse(String userID, boolean created, HttpStatus status) {
}

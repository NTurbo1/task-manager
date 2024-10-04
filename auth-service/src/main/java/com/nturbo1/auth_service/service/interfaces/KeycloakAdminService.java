package com.nturbo1.auth_service.service.interfaces;

import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;

import java.util.concurrent.CompletableFuture;

/**
 * Provides methods for interacting with a Keycloak server.
 *
 * @author NTurbo1
 */
public interface KeycloakAdminService {

    /**
     * Create a new user in the Keycloak server asynchronously.
     *
     * @param registerKeycloakUserRequest request body that contains necessary user information.
     * @return future value of true if the user is created and false otherwise.
     */
    CompletableFuture<Boolean> createUser(RegisterKeycloakUserRequest registerKeycloakUserRequest);
}

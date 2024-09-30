package com.nturbo1.auth_service.service.interfaces;

import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;

/**
 * Provides methods for interacting with a Keycloak server.
 *
 * @author NTurbo1
 */
public interface KeycloakAdminService {

    /**
     * Create a new user in the Keycloak server.
     *
     * @param registerKeycloakUserRequest request body that contains necessary user information.
     * @return True if the user is created and false otherwise.
     */
    boolean createUser(RegisterKeycloakUserRequest registerKeycloakUserRequest);
}

package com.nturbo1.auth_service.service.interfaces;

import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.response.CreateKeycloakUserResponse;
import org.keycloak.representations.idm.UserRepresentation;

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
	 * @return important information about the response and the user.
	 */
	CompletableFuture<CreateKeycloakUserResponse> createUser(
			RegisterKeycloakUserRequest registerKeycloakUserRequest);

	void deleteUser(String userId);

	UserRepresentation getUserByUsername(String username);
}

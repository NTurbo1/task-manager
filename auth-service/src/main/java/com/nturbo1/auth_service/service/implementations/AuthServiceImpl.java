package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.exception.exceptions.KeycloakAdminClientException;
import com.nturbo1.auth_service.exception.util.ExceptionMessage;
import com.nturbo1.auth_service.mapper.AuthMapper;
import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.request.RegisterUserRequest;
import com.nturbo1.auth_service.response.CreateKeycloakUserResponse;
import com.nturbo1.auth_service.service.interfaces.AuthService;
import com.nturbo1.auth_service.service.interfaces.KeycloakAdminService;
import com.nturbo1.auth_service.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

	private final KeycloakAdminService keycloakAdminService;
	private final UserService userService;

	private final AuthMapper authMapper;

	@Override
	public CompletableFuture<Boolean> registerUser(RegisterUserRequest registerUserRequest) {
		log.info("Received register user request: {}", registerUserRequest);
		RegisterKeycloakUserRequest keycloakUserRequest = authMapper.toKeycloakUserRequest(registerUserRequest);
		log.debug("Mapped register user request to keycloak create user request: {}", keycloakUserRequest);

		CreateKeycloakUserResponse keycloakUserCreated;
		try {

			// Create user in the Keycloak server first to get the user id
			keycloakUserCreated = keycloakAdminService.createUser(keycloakUserRequest).join();

			if (!keycloakUserCreated.created()) {
				log.info("Failed to create Keycloak user: {}", keycloakUserRequest);
				throw new ResponseStatusException(
						keycloakUserCreated.status(),
						String.format(
								ExceptionMessage.KEYCLOAK_ADMIN_CREATE_USER_FAILED.getMessage(),
								keycloakUserRequest.getUsername()));
			}
		} catch (ResponseStatusException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.info("An error happened during creation of Keycloak user: {}", keycloakUserRequest);
			log.error(e.getMessage());
			throw new KeycloakAdminClientException(
					String.format(
							ExceptionMessage.KEYCLOAK_ADMIN_CREATE_USER_FAILED.getMessage(),
							keycloakUserRequest.getUsername()));
		}

		AddUserRequest addUserRequest = authMapper.toAddUserRequest(registerUserRequest);
		log.debug("Mapped register user request to add user request: {}", addUserRequest);
		addUserRequest.setUserId(keycloakUserCreated.userID());
		log.debug("UserId of add user request is set to: {}", addUserRequest.getUserId());
		boolean userCreated;

		try {
			userCreated = userService.createUser(addUserRequest).join();

			if (!userCreated) {
				log.info("Trying to delete keycloak user with user id {}", keycloakUserCreated.userID());
				keycloakAdminService.deleteUser(keycloakUserCreated.userID());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			keycloakAdminService.deleteUser(keycloakUserCreated.userID());
			throw e;
		}

		return CompletableFuture.completedFuture(true);
	}
}

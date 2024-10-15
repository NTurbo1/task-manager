package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.mapper.AuthMapper;
import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.request.RegisterUserRequest;
import com.nturbo1.auth_service.response.CreateKeycloakUserResponse;
import com.nturbo1.auth_service.service.interfaces.AuthService;
import com.nturbo1.auth_service.service.interfaces.KeycloakAdminService;
import com.nturbo1.auth_service.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final KeycloakAdminService keycloakAdminService;
	private final UserService userService;

	private final AuthMapper authMapper;

	@Override
	public CompletableFuture<Boolean> registerUser(RegisterUserRequest registerUserRequest) {
		RegisterKeycloakUserRequest keycloakUserRequest =
				authMapper.toKeycloakUserRequest(registerUserRequest);

		// Create user in the Keycloak server first to get the user id
		CreateKeycloakUserResponse keycloakUserCreated =
				keycloakAdminService.createUser(keycloakUserRequest).join();

		if (!keycloakUserCreated.created()) {
			return CompletableFuture.completedFuture(false);
		}

		AddUserRequest addUserRequest = authMapper.toAddUserRequest(registerUserRequest);
		addUserRequest.setUserId(keycloakUserCreated.userID());
		boolean userCreated = userService.createUser(addUserRequest).join();

		if (!userCreated) {
			deleteKeycloakUser(keycloakUserCreated.userID());
			return CompletableFuture.completedFuture(false);
		}

		return CompletableFuture.completedFuture(true);
	}

	private void deleteKeycloakUser(String userId) {
		keycloakAdminService.deleteUser(userId);
	}
}

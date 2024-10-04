package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.mapper.AuthMapper;
import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.request.RegisterUserRequest;
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

    //TODO: Add transaction handler
    @Override
    public CompletableFuture<Boolean> registerUser(RegisterUserRequest registerUserRequest) {
        RegisterKeycloakUserRequest keycloakUserRequest = authMapper.toKeycloakUserRequest(registerUserRequest);
        CompletableFuture<Boolean> keycloakUserCreated = keycloakAdminService.createUser(keycloakUserRequest);

        AddUserRequest addUserRequest = authMapper.toAddUserRequest(registerUserRequest);
        CompletableFuture<Boolean> userCreated = userService.createUser(addUserRequest);

        return CompletableFuture.allOf(keycloakUserCreated, userCreated)
                .thenApply(v -> keycloakUserCreated.join() && userCreated.join());
    }
}

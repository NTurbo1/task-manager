package com.nturbo1.auth_service.service.interfaces;

import com.nturbo1.auth_service.request.RegisterUserRequest;

import java.util.concurrent.CompletableFuture;

/**
 * Provides methods for user authentication and authorization.
 */
public interface AuthService {

	CompletableFuture<Boolean> registerUser(RegisterUserRequest registerUserRequest);
}

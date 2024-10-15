package com.nturbo1.auth_service.service.interfaces;

import com.nturbo1.auth_service.request.AddUserRequest;

import java.util.concurrent.CompletableFuture;

/**
 * Provides methods for user service interaction.
 */
public interface UserService {

	/**
	 * Creates a new user data in the user service asynchronously.
	 *
	 * @param addUserRequest contains all the necessary data for the new user.
	 * @return future value of true if the process was successful and false otherwise.
	 */
	CompletableFuture<Boolean> createUser(AddUserRequest addUserRequest);
}

package com.nturbo1.user_service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddUserRequest {
	private String userId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String email;

	@NotNull
	private String password;
}

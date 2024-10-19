package com.nturbo1.auth_service.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterKeycloakUserRequest {
	private String username;
	private String password;
	private String email;

	@Override
	public String toString() {
		return "RegisterKeycloakUserRequest{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}

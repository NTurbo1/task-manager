package com.nturbo1.auth_service.request;

import lombok.Data;

@Data
public class AddUserRequest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}

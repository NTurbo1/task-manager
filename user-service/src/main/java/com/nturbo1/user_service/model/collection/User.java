package com.nturbo1.user_service.model.collection;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
@ToString
public class User {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Set<Role> roles;
}

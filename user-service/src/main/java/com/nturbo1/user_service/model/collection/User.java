package com.nturbo1.user_service.model.collection;

import java.util.Set;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@ToString
public class User {

  @Id private String id;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private Set<Role> roles;
}

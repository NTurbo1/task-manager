package com.nturbo1.user_service.controller.request;

import lombok.Data;

@Data
public class AddUserRequest {
  private String userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}

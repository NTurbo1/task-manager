package com.nturbo1.auth_service.exception.util;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
  USER_NOT_FOUND_BY_EMAIL("User not found by email %s"),
  INTERNAL_SERVER_ERROR("Internal Server Error"),
  REST_CLIENT_WENT_WRONG("Something went wrong during sending RestClient %s method to uri %s"),
  KEYCLOAK_ADMIN_CREATE_USER_FAILED("Keycloak admin client: user creation failed.");

  private final String message;

  ExceptionMessage(String message) {
    this.message = message;
  }
}

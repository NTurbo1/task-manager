package com.nturbo1.auth_service.exception.util;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
  USER_NOT_FOUND_BY_EMAIL("User not found by email %s"),
  INTERNAL_SERVER_ERROR("Internal Server Error"),
  REST_CLIENT_WENT_WRONG("Something went wrong during sending RestClient %s method to uri %s"),
  KEYCLOAK_ADMIN_CREATE_USER_FAILED("Keycloak admin client: create user by username %s failed"),
  KEYCLOAK_USER_NOT_FOUND_BY_USERNAME("Keycloak user not found by username %s"),
  KEYCLOAK_ADMIN_DELETE_USER_FAILED("Keycloak admin client: delete user by user id %s failed."),
  ;

  private final String message;

  ExceptionMessage(String message) {
    this.message = message;
  }
}

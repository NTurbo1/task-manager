package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.exception.exceptions.KeycloakAdminClientException;
import com.nturbo1.auth_service.exception.util.ExceptionMessage;
import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.response.CreateKeycloakUserResponse;
import com.nturbo1.auth_service.service.interfaces.KeycloakAdminService;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

  private Keycloak keycloak;

  @Value("${APP_SERVER_HOST}")
  private String appServerHost;

  @Value("${keycloak.server.port}")
  private int keycloakServerPort;

  @Value("${keycloak.realm}")
  private String realm;

  @Value("${keycloak.client.client-id}")
  private String clientId;

  @Value("${keycloak.client.client-secret}")
  private String clientSecret;

  @Value("${keycloak.client.grant-type}")
  private String grantType;

  @PostConstruct
  public void init() {
    String keycloakServerUrl =
        UriComponentsBuilder.newInstance()
            .scheme("http")
            .host(appServerHost)
            .port(keycloakServerPort)
            .toUriString();

    log.info("Initializing KeycloakAdminServiceImpl with serverUrl: {}", keycloakServerUrl);

    this.keycloak =
        KeycloakBuilder.builder()
            .serverUrl(keycloakServerUrl)
            .realm(realm)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .grantType(grantType)
            .build();
  }

  @Override
  @Async
  public CompletableFuture<CreateKeycloakUserResponse> createUser(
      RegisterKeycloakUserRequest registerKeycloakUserRequest) {
    UserRepresentation user = createUserRepresentationFrom(registerKeycloakUserRequest);
    return CompletableFuture.supplyAsync(() -> createUserInKeycloak(user));
  }

  @Override
  @Async
  public void deleteUser(String userId) {
    try (Response response = keycloak.realm(realm).users().delete(userId)) {
      if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
        log.info("Successfully deleted Keycloak user with userID: {}", userId);
      }
      // TODO: handle the failure case
    }
  }

  private CreateKeycloakUserResponse createUserInKeycloak(UserRepresentation user) {
    try (Response response = keycloak.realm(realm).users().create(user)) {
      log.info("Keycloak user created with username: {}", user.getUsername());
      return new CreateKeycloakUserResponse(
          extractUserIdFrom(response),
          response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL);
    } catch (Exception e) {
      log.error("Error creating user with Keycloak admin client", e);
      throw new KeycloakAdminClientException(
          ExceptionMessage.KEYCLOAK_ADMIN_CREATE_USER_FAILED.getMessage());
    }
  }

  private UserRepresentation createUserRepresentationFrom(RegisterKeycloakUserRequest userRequest) {
    UserRepresentation user = new UserRepresentation();
    user.setUsername(userRequest.getUsername());
    user.setEmail(userRequest.getEmail());
    user.setEnabled(true);
    user.setCredentials(List.of(createPasswordCredentialRepresentation(userRequest.getPassword())));

    return user;
  }

  private CredentialRepresentation createPasswordCredentialRepresentation(String password) {
    CredentialRepresentation passwordCred = new CredentialRepresentation();
    passwordCred.setType(CredentialRepresentation.PASSWORD);
    passwordCred.setValue(password);
    passwordCred.setTemporary(false);

    return passwordCred;
  }

  private String extractUserIdFrom(Response response) {
    String location = response.getHeaderString("Location");

    if (location != null) {
      String[] urlParts = location.split("/");
      return urlParts[urlParts.length - 1];
    }

    // TODO: handle the null case.
    return null;
  }
}

package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.service.interfaces.UserService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserServiceImpl implements UserService {

  @Value("${user.server.port}")
  private int userServicePort;

  private final RestClient restClient = RestClient.builder().build();

  @Override
  @Async
  public CompletableFuture<Boolean> createUser(AddUserRequest addUserRequest) {
    return CompletableFuture.supplyAsync(() -> isUserCreated(addUserRequest));
  }

  private boolean isUserCreated(AddUserRequest addUserRequest) {
    return restClient
        .post()
        .uri("http://localhost:" + userServicePort + "/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(addUserRequest)
        .exchange(
            (request, response) -> {
              // TODO: handle possible exceptions
              return response.getStatusCode().is2xxSuccessful();
            });
  }
}

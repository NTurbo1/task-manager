package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.exception.exceptions.RemoteInternalServiceException;
import com.nturbo1.auth_service.exception.handler.ErrorResponseBody;
import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
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
		String uri = "http://localhost:" + userServicePort + "/api/v1/users";
		log.info("Sending POST request to {} to create user {}", uri, addUserRequest);

		return restClient
				.post()
				.uri(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(addUserRequest)
				.exchange((request, response) -> {
							ErrorResponseBody responseBody = Objects.requireNonNull(response.bodyTo(ErrorResponseBody.class));
							log.debug("Response body: {}", responseBody);

							if (response.getStatusCode().is2xxSuccessful()) {
								log.info("Successfully created user {} at {}", addUserRequest, request.getURI());
								return true;
							}

							log.info("Failed to create user {} at {}", addUserRequest, request.getURI());
							if (response.getStatusCode().is5xxServerError()) {
								throw new RemoteInternalServiceException(responseBody.getError());
							} else {
								throw new ResponseStatusException(HttpStatusCode.valueOf(responseBody.getStatus()), responseBody.getError());
							}
						}
				);
	}
}

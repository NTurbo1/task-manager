package com.nturbo1.auth_service.service.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nturbo1.auth_service.exception.exceptions.RemoteInternalServiceException;
import com.nturbo1.auth_service.exception.handler.ErrorResponseBody;
import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	@Value("${user.server.port}")
	private int userServicePort;

	private final RestClient restClient = RestClient.builder().build();
	private final ObjectMapper objectMapper;

	@Override
	@Async
	public CompletableFuture<Boolean> createUser(AddUserRequest addUserRequest) throws RemoteInternalServiceException, ResponseStatusException {
		return CompletableFuture.supplyAsync(() -> isUserCreated(addUserRequest));
	}

	private boolean isUserCreated(AddUserRequest addUserRequest) throws RemoteInternalServiceException, ResponseStatusException {
		String uri = "http://localhost:" + userServicePort + "/api/v1/users";
		log.info("Sending POST request to {} to create user {}", uri, addUserRequest);

		return restClient
				.post()
				.uri(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(addUserRequest)
				.exchange((request, response) -> {
							if (response.getStatusCode().is2xxSuccessful()) {
								log.info("Successfully created user {} at {}", addUserRequest, request.getURI());
								return true;
							}

							log.info("Failed to create user {} at {}", addUserRequest, request.getURI());

							ErrorResponseBody responseBody = convertResponseBody(response);
							log.debug("Error response body: {}", responseBody);

							if (response.getStatusCode().is5xxServerError()) {
								throw new RemoteInternalServiceException(responseBody.getError());
							} else {
								throw new ResponseStatusException(HttpStatusCode.valueOf(responseBody.getStatus()), responseBody.getError());
							}
						}
				);
	}

	private ErrorResponseBody convertResponseBody(RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response) throws JsonProcessingException {
		String responseBody = response.bodyTo(String.class);
		return objectMapper.readValue(responseBody, ErrorResponseBody.class);
	}
}

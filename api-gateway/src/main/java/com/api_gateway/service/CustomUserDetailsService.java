package com.api_gateway.service;

import com.api_gateway.dto.UserDto;
import com.api_gateway.exception.util.ExceptionMessage;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${APP_SERVER_HOST}")
    private String appServerHost;
    @Value("${USER_SERVICE_PORT}")
    private String userServicePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }

    private UserDto getUserByEmail(String email) {
        RestClient restClient = RestClient.create();
        return restClient
                .get()
                .uri(createGetUserByEmailUri(email))
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> handleGetUserByEmail(request, response, email));
    }

    private URI createGetUserByEmailUri(String email) {
        return UriComponentsBuilder
                .fromUriString("http://"+ appServerHost + ":" + userServicePort + "/api/v1/users/auth")
                .queryParam("email", email)
                .build()
                .toUri();
    }

    private UserDto handleGetUserByEmail(HttpRequest request,
                                         RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response,
                                         String email) {
        try {
            if (response.getStatusCode().is4xxClientError()) {
                throw new UsernameNotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(), email));
            }
            if (response.getStatusCode().is5xxServerError()) {
                throw new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getMessage());
            }

            return response.bodyTo(UserDto.class);

        } catch (IOException e) {
            log.error(String.format(ExceptionMessage.REST_CLIENT_WENT_WRONG.getMessage(), request.getMethod(), request.getURI()), e);
            throw new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}

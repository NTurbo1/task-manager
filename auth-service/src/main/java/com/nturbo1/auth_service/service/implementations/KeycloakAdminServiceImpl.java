package com.nturbo1.auth_service.service.implementations;

import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.service.interfaces.KeycloakAdminService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    private final Keycloak keycloak;

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

    public KeycloakAdminServiceImpl() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://" + appServerHost + ":" + keycloakServerPort)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(grantType)
                .build();
    }

    @Override
    public boolean createUser(RegisterKeycloakUserRequest registerKeycloakUserRequest) {
        //TODO: Implement
        return false;
    }
}

package com.nturbo1.api_gateway.config.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.HttpMethod;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0.0", description = "Documentation API Gateway v1.0.0"))
public class OpenApiConfigs {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/v3/api-docs/user-service").and().method(HttpMethod.GET).uri("lb:http://USER-SERVICE"))
                .build();
    }

}

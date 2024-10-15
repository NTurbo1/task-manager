package com.nturbo1.api_gateway.config.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfigs {

	@Bean
	public OpenAPI customOpenAPI(
			@Value("${openapi.service.title}") String serviceTitle,
			@Value("${openapi.service.version}") String serviceVersion) {
		return new OpenAPI()
				.info(
						new Info()
								.title(serviceTitle)
								.version(serviceVersion)
								.summary("Documentation API gateway v" + serviceVersion)
								.description(
										"This is Gateway API. It contains endpoints for user authentication/authorization "
												+ "and user registration."));
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(
						r ->
								r.path("/v3/api-docs/user-service")
										.and()
										.method(HttpMethod.GET)
										.uri("lb:http://USER-SERVICE"))
				.build();
	}
}

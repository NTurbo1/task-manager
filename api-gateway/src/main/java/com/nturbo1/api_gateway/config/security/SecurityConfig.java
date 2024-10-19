package com.nturbo1.api_gateway.config.security;

import com.nturbo1.api_gateway.config.security.oauth2.JwtConverter;
import com.nturbo1.api_gateway.config.security.util.ApiUri;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtConverter jwtConverter;

	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {

		http.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.authorizeExchange(
						authorizeExchangeSpec ->
								authorizeExchangeSpec
										.pathMatchers(ApiUri.PUBLIC_URIS.toArray(new String[0]))
										.permitAll()
										.anyExchange()
										.authenticated())
				.oauth2ResourceServer(auth -> auth.jwt(token -> token.jwtAuthenticationConverter(jwtConverter)))
				.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
				.logout(ServerHttpSecurity.LogoutSpec::disable);

		return http.build();
	}
}

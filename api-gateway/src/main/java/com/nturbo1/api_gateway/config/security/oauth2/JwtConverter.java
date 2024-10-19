package com.nturbo1.api_gateway.config.security.oauth2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

	public JwtConverter() {
		this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	}

	@Override
	public Mono<AbstractAuthenticationToken> convert(@NonNull Jwt jwt) {
		final Set<GrantedAuthority> authorities =
				Stream.concat(
						Objects.requireNonNull(jwtGrantedAuthoritiesConverter.convert(jwt)).stream(),
						extractResourceRoles(jwt).stream()
				).collect(Collectors.toSet());
		return Mono.just(new JwtAuthenticationToken(jwt, authorities));
	}

	private Set<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
		final Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		final Map<String, List<String>> eternal = (Map<String, List<String>>) resourceAccess.get("account");
		final List<String> roles = eternal.get("roles");

		if (!roles.isEmpty()) {
			return roles.stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase().replace("-", "_")))
					.collect(Collectors.toSet());
		}

		return Collections.emptySet();
	}
}

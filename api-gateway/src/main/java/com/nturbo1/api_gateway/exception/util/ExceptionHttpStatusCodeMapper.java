package com.nturbo1.api_gateway.exception.util;

import jakarta.ws.rs.InternalServerErrorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionHttpStatusCodeMapper {

	private static final Map<Class<? extends Throwable>, HttpStatusCode>
			EXCEPTION_TO_HTTP_STATUS_CODE_MAPPER =
			Map.ofEntries(
					Map.entry(UsernameNotFoundException.class, HttpStatus.UNAUTHORIZED),
					Map.entry(InternalServerErrorException.class, HttpStatus.INTERNAL_SERVER_ERROR));

	public static HttpStatusCode getHttpStatus(Throwable throwable) {
		return EXCEPTION_TO_HTTP_STATUS_CODE_MAPPER.getOrDefault(
				throwable.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

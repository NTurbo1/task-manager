package com.nturbo1.task_service.exception.handler;

import com.nturbo1.task_service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseBody> handleResourceNotFound(
			HttpServletRequest request, ResourceNotFoundException exception) {
		ErrorResponseBody responseBody =
				createErrorResponseBody(HttpStatus.NOT_FOUND, request, exception);

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseBody);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponseBody> handleUnknownException(
			HttpServletRequest request, Exception exception) {
		ErrorResponseBody responseBody =
				createErrorResponseBody(HttpStatus.INTERNAL_SERVER_ERROR, request, exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.contentType(MediaType.APPLICATION_JSON)
				.body(responseBody);
	}

	private ErrorResponseBody createErrorResponseBody(
			HttpStatus status, HttpServletRequest request, Exception exception) {
		return ErrorResponseBody.builder()
				.timestamp(LocalDateTime.now())
				.status(status.value())
				.error(exception.getMessage())
				.path(request.getRequestURI())
				.build();
	}
}

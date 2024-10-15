package com.nturbo1.user_service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponseBody {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String path;
}

package com.nturbo1.auth_service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class ErrorResponseBody {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    @Override
    public String toString() {
        // In JSON format
        return "{" +
                    "\"timestamp\": \"" + timestamp + "\"," +
                    "\"status\": \"" + status + "\"," +
                    "\"error\": \"" + error + "\"," +
                    "\"path\": \"" + path + "\"" +
                "}";
    }
}

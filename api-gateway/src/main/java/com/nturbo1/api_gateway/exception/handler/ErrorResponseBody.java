package com.nturbo1.api_gateway.exception.handler;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ErrorResponseBody {

    private LocalDateTime timestamp;
    private String status;
    private String message;
    private String path;

    @Override
    public String toString() {
        // In JSON format
        return "{" +
                    "\"timestamp\": \"" + timestamp + "\"," +
                    "\"status\": \"" + status + "\"," +
                    "\"message\": \"" + message + "\"," +
                    "\"path\": \"" + path + "\"" +
                "}";
    }
}

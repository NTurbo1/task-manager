package com.nturbo1.api_gateway.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

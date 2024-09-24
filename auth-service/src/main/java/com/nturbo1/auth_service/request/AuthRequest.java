package com.nturbo1.auth_service.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

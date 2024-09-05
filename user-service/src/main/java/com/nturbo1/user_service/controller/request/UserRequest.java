package com.nturbo1.user_service.controller.request;

import lombok.Data;

@Data
public class UserRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
}

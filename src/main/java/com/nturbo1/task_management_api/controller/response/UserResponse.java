package com.nturbo1.task_management_api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
}

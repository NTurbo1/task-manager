package com.nturbo1.user_service.service.dto;

import lombok.Data;

@Data
public class AddUserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

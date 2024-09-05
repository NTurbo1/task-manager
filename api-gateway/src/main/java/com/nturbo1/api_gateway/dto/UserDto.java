package com.nturbo1.api_gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
}

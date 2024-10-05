package com.nturbo1.user_service.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAuthDto {

  private String email;
  private String password;
}

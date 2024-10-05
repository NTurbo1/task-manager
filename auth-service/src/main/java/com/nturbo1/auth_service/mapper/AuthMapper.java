package com.nturbo1.auth_service.mapper;

import com.nturbo1.auth_service.request.AddUserRequest;
import com.nturbo1.auth_service.request.RegisterKeycloakUserRequest;
import com.nturbo1.auth_service.request.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

  @Mapping(source = "email", target = "username")
  @Mapping(source = "email", target = "email")
  RegisterKeycloakUserRequest toKeycloakUserRequest(RegisterUserRequest userRequest);

  AddUserRequest toAddUserRequest(RegisterUserRequest userRequest);
}

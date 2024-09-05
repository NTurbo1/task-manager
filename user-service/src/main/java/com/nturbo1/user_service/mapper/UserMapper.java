package com.nturbo1.user_service.mapper;

import com.nturbo1.user_service.controller.request.UserRequest;
import com.nturbo1.user_service.controller.response.UserResponse;
import com.nturbo1.user_service.model.collection.User;
import com.nturbo1.user_service.service.dto.UserAuthDto;
import com.nturbo1.user_service.service.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(UserDto dto);
    List<UserResponse> toResponseList(List<UserDto> dtoList);

    UserDto toDto(User model);
    List<UserDto> toDtoList(List<User> modelList);

    UserDto toDto(UserRequest request);

    User toModel(UserDto dto);
    User toModel(UserRequest request);

    UserAuthDto toAuthDto(User model);
}

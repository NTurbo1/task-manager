package com.nturbo1.task_management_api.mapper;

import com.nturbo1.task_management_api.controller.response.UserResponse;
import com.nturbo1.task_management_api.model.collection.User;
import com.nturbo1.task_management_api.service.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(UserDto dto);
    List<UserResponse> toResponseList(List<UserDto> dtoList);

    UserDto toDto(User model);
    List<UserDto> toDtoList(List<User> modelList);
}

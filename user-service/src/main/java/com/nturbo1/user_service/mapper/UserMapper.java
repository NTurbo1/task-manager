package com.nturbo1.user_service.mapper;

import com.nturbo1.user_service.controller.request.AddUserRequest;
import com.nturbo1.user_service.controller.request.UpdateUserRequest;
import com.nturbo1.user_service.controller.response.UserResponse;
import com.nturbo1.user_service.model.collection.User;
import com.nturbo1.user_service.service.dto.AddUserDto;
import com.nturbo1.user_service.service.dto.UserAuthDto;
import com.nturbo1.user_service.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(UserDto dto);
    List<UserResponse> toResponseList(List<UserDto> dtoList);

    UserDto toDto(User model);
    List<UserDto> toDtoList(List<User> modelList);

    @Mapping(source = "userId", target = "id")
    AddUserDto toDto(AddUserRequest request);

    UserDto toDto(UpdateUserRequest request);

    User toModel(AddUserDto dto);

    UserAuthDto toAuthDto(User model);

    @Mapping(source = "id", target = "id", ignore = true)
    User updateFromDto(UserDto dto, @MappingTarget User model);
}

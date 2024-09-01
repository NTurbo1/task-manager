package com.nturbo1.user_service.service.interfaces;

import com.nturbo1.user_service.service.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto deleteUser(String id);
}

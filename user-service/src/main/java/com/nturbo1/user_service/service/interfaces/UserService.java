package com.nturbo1.user_service.service.interfaces;

import com.nturbo1.user_service.service.dto.AddUserDto;
import com.nturbo1.user_service.service.dto.UserAuthDto;
import com.nturbo1.user_service.service.dto.UserDto;

import java.util.List;

public interface UserService {

	List<UserDto> getAllUsers();

	UserDto getUserById(String id);

	UserAuthDto getUserAuthByEmail(String email);

	UserDto createUser(AddUserDto userDto);

	UserDto updateUser(String userId, UserDto userDto);

	void deleteUser(String id);
}

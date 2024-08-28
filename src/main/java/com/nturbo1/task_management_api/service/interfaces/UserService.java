package com.nturbo1.task_management_api.service.interfaces;

import com.nturbo1.task_management_api.service.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
}

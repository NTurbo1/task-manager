package com.nturbo1.task_management_api.service.implementations;

import com.nturbo1.task_management_api.exception.ResourceNotFoundException;
import com.nturbo1.task_management_api.exception.util.ExceptionMessage;
import com.nturbo1.task_management_api.mapper.UserMapper;
import com.nturbo1.task_management_api.model.collection.User;
import com.nturbo1.task_management_api.repository.UserRepository;
import com.nturbo1.task_management_api.service.dto.UserDto;
import com.nturbo1.task_management_api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND_BY_ID, id)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND_BY_EMAIL, email)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // TODO: implement
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        // TODO: implement
        return null;
    }

    @Override
    public UserDto deleteUser(String id) {
        // TODO: implements
        return null;
    }
}

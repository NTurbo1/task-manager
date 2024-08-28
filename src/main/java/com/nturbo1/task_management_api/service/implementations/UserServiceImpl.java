package com.nturbo1.task_management_api.service.implementations;

import com.nturbo1.task_management_api.mapper.UserMapper;
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
}

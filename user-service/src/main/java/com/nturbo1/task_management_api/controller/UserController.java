package com.nturbo1.task_management_api.controller;

import com.nturbo1.task_management_api.controller.response.UserResponse;
import com.nturbo1.task_management_api.mapper.UserMapper;
import com.nturbo1.task_management_api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userMapper.toResponseList(userService.getAllUsers());
        return ResponseEntity.ok(users);
    }
}

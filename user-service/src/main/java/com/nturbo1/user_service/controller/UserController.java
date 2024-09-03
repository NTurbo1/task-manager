package com.nturbo1.user_service.controller;

import com.nturbo1.user_service.controller.response.UserResponse;
import com.nturbo1.user_service.mapper.UserMapper;
import com.nturbo1.user_service.service.dto.UserAuthDto;
import com.nturbo1.user_service.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/users/auth")
    public ResponseEntity<UserAuthDto> getUserByEmailSecure(@RequestParam String email) {
        UserAuthDto userAuthDto = userService.getUserAuthByEmail(email);
        return ResponseEntity.ok(userAuthDto);
    }
}

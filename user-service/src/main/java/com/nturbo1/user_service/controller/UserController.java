package com.nturbo1.user_service.controller;

import com.nturbo1.user_service.controller.request.AddUserRequest;
import com.nturbo1.user_service.controller.response.UserResponse;
import com.nturbo1.user_service.mapper.UserMapper;
import com.nturbo1.user_service.repository.UserRepository;
import com.nturbo1.user_service.service.dto.UserAuthDto;
import com.nturbo1.user_service.service.dto.UserDto;
import com.nturbo1.user_service.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userMapper.toResponseList(userService.getAllUsers());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/auth")
    public ResponseEntity<UserAuthDto> getUserByEmailSecure(@RequestParam String email) {
        log.debug("UserController.getUserByEmailSecure(String email) ------- Email: {}", email);
        UserAuthDto userAuthDto = userService.getUserAuthByEmail(email);
        log.debug("UserController.getUserByEmailSecure(String email) ------- UserAuthDto: {}", userAuthDto);

        return ResponseEntity.ok(userAuthDto);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody AddUserRequest addUserRequest) {
        log.debug("UserController.createUser(AddUserRequest) ------- AddUserRequest: {}", addUserRequest);
        UserDto newUserDto = userService.createUser(userMapper.toDto(addUserRequest));
        log.debug("UserController.createUser(AddUserRequest) ------- newly created UserDto: {}", newUserDto);

        return ResponseEntity.ok(userMapper.toResponse(newUserDto));
    }
}

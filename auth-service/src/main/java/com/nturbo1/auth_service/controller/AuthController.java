package com.nturbo1.auth_service.controller;

import com.nturbo1.auth_service.request.RegisterUserRequest;
import com.nturbo1.auth_service.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/auth/register")
  public ResponseEntity<Boolean> registerUser(
      @RequestBody RegisterUserRequest registerUserRequest) {
    boolean success = authService.registerUser(registerUserRequest).join();
    return ResponseEntity.ok(success);
  }
}

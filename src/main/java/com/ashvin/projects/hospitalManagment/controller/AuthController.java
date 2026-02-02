package com.ashvin.projects.hospitalManagment.controller;

import com.ashvin.projects.hospitalManagment.dto.LoginRequestDto;
import com.ashvin.projects.hospitalManagment.dto.LoginResponseDto;
import com.ashvin.projects.hospitalManagment.dto.SignUpRequestDto;
import com.ashvin.projects.hospitalManagment.dto.SignupResponseDto;
import com.ashvin.projects.hospitalManagment.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) throws IllegalAccessException {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}

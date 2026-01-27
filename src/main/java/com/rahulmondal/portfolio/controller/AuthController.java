package com.rahulmondal.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.requests.UserLoginRequestDTO;
import com.rahulmondal.portfolio.dto.requests.UserRegistrationRequestDTO;
import com.rahulmondal.portfolio.dto.response.UserLoginResponseDTO;
import com.rahulmondal.portfolio.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseEntity.ok(authService.login(userLoginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        return ResponseEntity.ok(authService.signup(userRegistrationRequestDTO));
    }
    
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkValidity() {
        return ResponseEntity.ok(authService.check());
    }
    
}

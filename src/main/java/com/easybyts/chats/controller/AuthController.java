package com.easybyts.chats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybyts.chats.controller.Auth.AuthService;
import com.easybyts.chats.controller.Auth.Login.LoginRequest;
import com.easybyts.chats.controller.Auth.Register.RegisterRequest;

@RestController
@RequestMapping("/api/auth") // handle authentication requests
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService; // needed! we don't want to make this file full of code
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) { 
        return authService.register(request);
    }
}

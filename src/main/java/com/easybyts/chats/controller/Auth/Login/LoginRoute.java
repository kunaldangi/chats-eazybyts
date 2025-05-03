package com.easybyts.chats.controller.Auth.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.easybyts.chats.model.User;
import com.easybyts.chats.repository.UserRepository;
import com.easybyts.chats.util.JwtUtil;
import com.easybyts.chats.util.PasswordEncrypt;

public class LoginRoute {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> handleRoute(LoginRequest request) {
        if(request.getEmail() == null || request.getEmail().isEmpty()) return ResponseEntity.badRequest().body(new LoginResponse("error", "Email is required!"));
        if(request.getPassword() == null || request.getPassword().isEmpty()) return ResponseEntity.badRequest().body(new LoginResponse("error", "Password is required!"));

        System.out.println("Email: "+ request.getEmail() + " Password!: " + request.getPassword());
        return ResponseEntity.ok(new LoginResponse("success", "Login successful!"));
    }
}

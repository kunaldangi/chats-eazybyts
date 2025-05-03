package com.easybyts.chats.controller.Auth.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.easybyts.chats.model.User;
import com.easybyts.chats.repository.UserRepository;
import com.easybyts.chats.util.JwtUtil;
import com.easybyts.chats.util.PasswordEncrypt;

public class RegisterRoute {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> handleRoute(RegisterRequest request) {
        if(request.getUsername() == null || request.getUsername().isEmpty()) return ResponseEntity.badRequest().body(new RegisterResponse("error", "Username is required!"));
        if(request.getEmail() == null || request.getEmail().isEmpty()) return ResponseEntity.badRequest().body(new RegisterResponse("error", "Email is required!"));
        if(request.getPassword() == null || request.getPassword().isEmpty()) return ResponseEntity.badRequest().body(new RegisterResponse("error", "Password is required!"));

        

        System.out.println("Name: "+ request.getUsername() + " Email: " + request.getEmail() + " Password: " + request.getPassword());
        
        return ResponseEntity.ok(new RegisterResponse("success", "Registration successful"));
    }
}
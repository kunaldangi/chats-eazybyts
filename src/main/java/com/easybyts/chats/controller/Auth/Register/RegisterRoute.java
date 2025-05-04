package com.easybyts.chats.controller.Auth.Register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.easybyts.chats.model.User;
import com.easybyts.chats.repository.UserRepository;
import com.easybyts.chats.util.JwtUtil;
import com.easybyts.chats.util.PasswordEncrypt;

@Component
public class RegisterRoute {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    public ResponseEntity<?> handleRoute(RegisterRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty()) return ResponseEntity.badRequest().body(new RegisterResponse("error", "Username is required!"));
        if (request.getEmail() == null || request.getEmail().isEmpty()) return ResponseEntity.badRequest().body(new RegisterResponse("error", "Email is required!"));
        if (request.getPassword() == null || request.getPassword().isEmpty()) return ResponseEntity.badRequest().body(new RegisterResponse("error", "Password is required!"));

        Optional<User> existing = this.userRepository.findByEmailAndUsername(request.getEmail(), request.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body(new RegisterResponse("error", "User already exists!"));
        }
        
        try {
            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(PasswordEncrypt.hashPassword(request.getPassword()));
            userRepository.save(newUser);
            return ResponseEntity.ok(new RegisterResponse("success", "Registration successful!"));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new RegisterResponse("error", "Internal server error"));
        }
    }
}

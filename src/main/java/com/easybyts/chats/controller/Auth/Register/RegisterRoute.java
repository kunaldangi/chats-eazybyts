package com.easybyts.chats.controller.Auth.Register;

import org.springframework.http.ResponseEntity;

public class RegisterRoute {
    public ResponseEntity<?> handleRoute(RegisterRequest request) {
        System.out.println("Name: "+ request.getName() + " Email: " + request.getEmail() + " Password: " + request.getPassword());
        
        return ResponseEntity.ok("Registration successful"); // sends a response to user
    }
}
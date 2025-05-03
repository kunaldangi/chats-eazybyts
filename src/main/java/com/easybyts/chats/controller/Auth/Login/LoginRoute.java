package com.easybyts.chats.controller.Auth.Login;

import org.springframework.http.ResponseEntity;

public class LoginRoute {
    public ResponseEntity<?> handleRoute(LoginRequest request) {
        System.out.println("Email: "+ request.getEmail() + " Password: " + request.getPassword());

        return ResponseEntity.ok(new LoginResponse("success")); // sends a response to user
    }
}

package com.easybyts.chats.controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import com.easybyts.chats.controller.Auth.Login.LoginRequest;
import com.easybyts.chats.controller.Auth.Register.RegisterRequest;
import com.easybyts.chats.controller.Auth.Register.RegisterRoute;

import jakarta.servlet.http.HttpServletResponse;

import com.easybyts.chats.controller.Auth.Login.LoginRoute;

@Component
public class AuthService {

    private final LoginRoute loginRoute;
    private final RegisterRoute registerRoute;

    @Autowired
    public AuthService(LoginRoute loginRoute, RegisterRoute registerRoute) {
        this.loginRoute =  loginRoute;
        this.registerRoute = registerRoute;
    }

    public ResponseEntity<?> login(LoginRequest request, HttpServletResponse response) {
        return loginRoute.handleRoute(request, response);
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        return registerRoute.handleRoute(request);
    }
}

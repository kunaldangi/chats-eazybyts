package com.easybyts.chats.controller.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.easybyts.chats.controller.Auth.Login.LoginRequest;
import com.easybyts.chats.controller.Auth.Register.RegisterRequest;
import com.easybyts.chats.controller.Auth.Register.RegisterRoute;
import com.easybyts.chats.controller.Auth.Login.LoginRoute;

@Service
public class AuthService {

    private final LoginRoute loginRoute;
    private final RegisterRoute registerRoute;

    public AuthService() {
        this.loginRoute =  new LoginRoute();
        this.registerRoute = new RegisterRoute();
    }

    public ResponseEntity<?> login(LoginRequest request) {
        return loginRoute.handleRoute(request);
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        return registerRoute.handleRoute(request);
    }
}

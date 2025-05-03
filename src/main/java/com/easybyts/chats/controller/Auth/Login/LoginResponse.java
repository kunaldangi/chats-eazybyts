package com.easybyts.chats.controller.Auth.Login;

public class LoginResponse {
    private String status;

    public LoginResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

package com.easybyts.chats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybyts.chats.controller.User.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users") // for sending user data
public class UserController {

    private final UserService userService;
    
    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) {
        return userService.users(request, response);
    }
}

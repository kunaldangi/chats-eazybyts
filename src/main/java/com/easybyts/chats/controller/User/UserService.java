package com.easybyts.chats.controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.easybyts.chats.controller.User.GetUsers.GetUsersRoute;
import com.easybyts.chats.repository.UserRepository;

@Component 
public class UserService {
    private final GetUsersRoute getUsers;

    @Autowired private UserRepository userRepository;

    @Autowired
    public UserService(GetUsersRoute getUsers) {
        this.getUsers = getUsers;
    }

    public ResponseEntity<?> users(HttpServletRequest request, HttpServletResponse response) {
        return getUsers.handleRoute(request, response);
    }
}
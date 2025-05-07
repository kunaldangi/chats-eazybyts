package com.easybyts.chats.controller.User.GetUsers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.easybyts.chats.DTO.UserSessionData;
import com.easybyts.chats.controller.User.GetUsers.DTO.ErrorResponse;
import com.easybyts.chats.controller.User.GetUsers.DTO.GetUsers;
import com.easybyts.chats.controller.User.GetUsers.DTO.GetUsersResponse;
import com.easybyts.chats.repository.UserRepository;
import com.easybyts.chats.websocket.WebSocketSessionManager;

@Service
public class GetUsersRoute {
    @Autowired private UserRepository userRepository;

    public ResponseEntity<?> handleRoute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserSessionData userSessionData = (UserSessionData) request.getAttribute("user");
            Long userId = userSessionData.getId();
            
            List<GetUsers> users = userRepository.findByIdNot(userId).stream()
                .map(user -> new GetUsers(user.getId(), user.getUsername(), user.getEmail(), WebSocketSessionManager.isUserOnline(user.getId())))
                .toList();

            return ResponseEntity.ok(new GetUsersResponse("success", users));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("error", "An error occurred!"));
        }
    }
}


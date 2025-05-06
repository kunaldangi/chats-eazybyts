package com.easybyts.chats.websocket;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.easybyts.chats.websocket.DTO.OnlineUser;

@Service
public class WebSocketMessageService {

    public void sendMessageToUser(Long userId, String message) {
        OnlineUser user = WebSocketSessionManager.getUser(userId);
        if (user != null && user.getSession().isOpen()) {
            try {
                user.getSession().sendMessage(new TextMessage(message));
            } catch (IOException e) {
                System.err.println("Failed to send message to user " + userId + ": " + e.getMessage());
            }
        }
    }

    public void broadcastMessage(String message) {
        WebSocketSessionManager.getAllUsers().forEach((userId, user) -> {
            if (user.getSession().isOpen()) {
                try {
                    user.getSession().sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    System.err.println("Failed to send to " + user.getUsername() + ": " + e.getMessage());
                }
            }
        });
    }
}

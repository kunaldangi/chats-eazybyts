package com.easybyts.chats.websocket;

import java.util.List;
import java.util.Map;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.easybyts.chats.util.JwtUtil;
import com.easybyts.chats.websocket.DTO.MessagePayload;
import com.easybyts.chats.websocket.DTO.OnlineUser;

public class AppWebSocketHandler extends TextWebSocketHandler {

    private final JwtUtil jwtUtil;
    private final WebSocketMessageService webSocketMessageService;

    public AppWebSocketHandler() {
        this.jwtUtil = new JwtUtil();
        this.webSocketMessageService = new WebSocketMessageService();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String jwt = getJwtFromCookies(session);
        try {
            String data = jwtUtil.validateTokenAndGetSubject(jwt);

            Map<String, Object> userData = new ObjectMapper().readValue(data, new TypeReference<Map<String, Object>>() {});
            session.getAttributes().put("user", userData); // add the date so that we can use it later

            Long userId = ((Number) userData.get("id")).longValue();
            String email = (String) userData.get("email");
            String username = (String) userData.get("username");

            MessagePayload messagePayload = new MessagePayload("online_user_added", data);
            String message = new ObjectMapper().writeValueAsString(messagePayload);
            webSocketMessageService.broadcastMessage(message);

            OnlineUser user = new OnlineUser(userId, email, username, session);
            WebSocketSessionManager.addUser(user);

            System.out.println(username + " connected. Total Online Users: " + WebSocketSessionManager.getTotalOnlineUsers());
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
            session.close(CloseStatus.POLICY_VIOLATION);
            return;
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("📨 Received: " + message.getPayload());
        // session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Map<String, Object> userData = (Map<String, Object>) session.getAttributes().get("user");
        
        Long userId = ((Number) userData.get("id")).longValue();
        String username = (String) userData.get("username");
        WebSocketSessionManager.removeUser(userId);

        String data = new ObjectMapper().writeValueAsString(userData);
        MessagePayload messagePayload = new MessagePayload("online_user_removed", data);
        String message = new ObjectMapper().writeValueAsString(messagePayload);
        webSocketMessageService.broadcastMessage(message);

        System.out.println(username + " disconnected. Total Online Users: " + WebSocketSessionManager.getTotalOnlineUsers());
    }

    private String getJwtFromCookies(WebSocketSession session) {
        List<String> cookieHeaders = session.getHandshakeHeaders().get("cookie");
        if (cookieHeaders == null) return null;
    
        for (String header : cookieHeaders) {
            String[] cookies = header.split(";");
            for (String cookie : cookies) {
                String[] parts = cookie.trim().split("=");
                if (parts.length == 2 && parts[0].equals("session")) {
                    return parts[1];
                }
            }
        }
        return null;
    }
}

package com.easybyts.chats.websocket.DTO;

import org.springframework.web.socket.WebSocketSession;

public class OnlineUser {
    private Long id;
    private String email;
    private String username;
    private WebSocketSession session;

    public OnlineUser(Long id, String email, String username, WebSocketSession session) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.session = session;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public WebSocketSession getSession() { return session; }
}

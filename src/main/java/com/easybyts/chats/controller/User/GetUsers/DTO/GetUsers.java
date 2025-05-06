package com.easybyts.chats.controller.User.GetUsers.DTO;

public class GetUsers {
    public Long id;
    public String username;
    public String email;
    public String password;
    public Boolean isOnline;
    public java.time.LocalDateTime createdAt;
    public java.time.LocalDateTime updatedAt;

    public GetUsers(Long id, String username, String email, Boolean isOnline) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isOnline = isOnline;
    }

    public GetUsers(Long id, String username, String email) {
        this(id, username, email, false);
    }
}

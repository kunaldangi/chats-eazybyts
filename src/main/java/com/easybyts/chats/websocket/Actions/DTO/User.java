package com.easybyts.chats.websocket.Actions.DTO;

public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Boolean isOnline;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;

    public User() { }
    public User(Long id, String email, String username, String password, java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, Boolean isOnline) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isOnline = isOnline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }
    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

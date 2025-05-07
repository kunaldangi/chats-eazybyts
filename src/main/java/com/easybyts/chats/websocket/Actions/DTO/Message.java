package com.easybyts.chats.websocket.Actions.DTO;

public class Message {
    private User to;
    private String message;

    public Message() { }
    public Message(User to, String message) {
        this.to = to;
        this.message = message;
    }

    public User getTo() {
        return to;
    }
    public void setTo(User to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

package com.easybyts.chats.controller.Messages.GetMessages.DTO;

import java.util.List;

import com.easybyts.chats.model.Messages;

public class GetMessagesResponse {
    private String status;
    private List<Messages> messages;

    public GetMessagesResponse(String status, List<Messages> messages) {
        this.status = status;
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }
}

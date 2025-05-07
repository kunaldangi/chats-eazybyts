package com.easybyts.chats.controller.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.easybyts.chats.controller.Messages.GetMessages.GetMessagesRoute;
import com.easybyts.chats.controller.Messages.GetMessages.DTO.GetMessagesBody;

@Component
public class MessagesService {
    private final GetMessagesRoute getMessages;

    @Autowired
    public MessagesService(GetMessagesRoute getMessages) {
        this.getMessages = getMessages;
    }

    public ResponseEntity<?> messages(HttpServletRequest request, HttpServletResponse response, @RequestBody GetMessagesBody getMessagesBody) {
        return getMessages.handleRoute(request, response, getMessagesBody);
    }
}

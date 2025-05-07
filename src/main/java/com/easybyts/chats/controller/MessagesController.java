package com.easybyts.chats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.easybyts.chats.controller.Messages.MessagesService;
import com.easybyts.chats.controller.Messages.GetMessages.DTO.GetMessagesBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    
    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/")
    public ResponseEntity<?> getMessages(HttpServletRequest request, HttpServletResponse response, @RequestBody GetMessagesBody getMessagesBody) {
        return messagesService.messages(request, response, getMessagesBody);
    }
}

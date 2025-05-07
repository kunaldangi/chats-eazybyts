package com.easybyts.chats.controller.Messages.GetMessages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.easybyts.chats.DTO.UserSessionData;
import com.easybyts.chats.controller.Messages.GetMessages.DTO.ErrorResponse;
import com.easybyts.chats.controller.Messages.GetMessages.DTO.GetMessagesBody;
import com.easybyts.chats.controller.Messages.GetMessages.DTO.GetMessagesResponse;
import com.easybyts.chats.model.Messages;
import com.easybyts.chats.repository.MessagesRepository;


@Service
public class GetMessagesRoute {

    @Autowired private MessagesRepository messagesRepository;

    public ResponseEntity<?> handleRoute(HttpServletRequest request, HttpServletResponse response, @RequestBody GetMessagesBody getMessagesBody) {
        try {
            UserSessionData userSessionData = (UserSessionData) request.getAttribute("user");
            List<Messages> messages = messagesRepository.findChatBetweenUsersById(userSessionData.getId(), getMessagesBody.getId());

            if (messages == null || messages.isEmpty()) {
                return ResponseEntity.ok(new ErrorResponse("error", "No messages found!"));
            }

            List<Messages> messagesList = messages.stream().collect(java.util.stream.Collectors.toList());
            return ResponseEntity.ok(new GetMessagesResponse("success", messagesList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("error", "An error occurred!"));
        }
    }
}

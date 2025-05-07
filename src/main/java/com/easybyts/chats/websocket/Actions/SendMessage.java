package com.easybyts.chats.websocket.Actions;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.easybyts.chats.model.Messages;
import com.easybyts.chats.repository.MessagesRepository;
import com.easybyts.chats.websocket.WebSocketMessageService;
import com.easybyts.chats.websocket.Actions.DTO.Message;
import com.easybyts.chats.websocket.Actions.DTO.User;
import com.easybyts.chats.websocket.DTO.MessagePayload;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component // in order to use @Autowired to inject the MessagesRepository
public class SendMessage {

    @Autowired private MessagesRepository messagesRepository;
    private WebSocketMessageService webSocketMessageService;

    @Autowired
    public SendMessage(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    public void handleMessage(WebSocketSession session, String dataString) {
        try {
            Message message = new ObjectMapper().readValue(dataString, Message.class);
            User receiveUser = message.getTo();
            System.out.println("Message received: " + message.getMessage());
            System.out.println("Receiver: " + receiveUser.getUsername());

            Map<String, Object> userData = (Map<String, Object>) session.getAttributes().get("user");
            System.out.println("Sender: " + userData.get("username"));

            Long senderId = Long.valueOf(userData.get("id").toString());
            Long receiverId = receiveUser.getId();

            Messages newMessage = new Messages();
            newMessage.setContent(message.getMessage());
            newMessage.setSenderId(senderId);
            newMessage.setReceiverId(receiverId);
            messagesRepository.save(newMessage);

            MessagePayload senderPayload = new MessagePayload("message_sent", message.getMessage());
            String senderPayloadString = new ObjectMapper().writeValueAsString(senderPayload);
            webSocketMessageService.sendMessageToUser(senderId, senderPayloadString);

            MessagePayload receiverPayload = new MessagePayload("message_received", message.getMessage());
            String receiverPayloadString = new ObjectMapper().writeValueAsString(receiverPayload);
            webSocketMessageService.sendMessageToUser(receiverId, receiverPayloadString);

        } catch (Exception e) {
            System.err.println("Failed to process JSON: " + e.getMessage());
        }
    }
}

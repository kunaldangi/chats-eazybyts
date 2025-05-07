package com.easybyts.chats.websocket.DTO;

public class MessagePayload {
    /*
        Server Format
            type: "online_user_added", "online_user_removed"
            type: "message_sent", "message_received"
    */

    private String type;
    private String content;

    public MessagePayload() {}

    public MessagePayload(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

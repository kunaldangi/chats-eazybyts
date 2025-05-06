package com.easybyts.chats.websocket;

import java.util.concurrent.ConcurrentHashMap;

import com.easybyts.chats.websocket.DTO.OnlineUser;

public class WebSocketSessionManager {
    private static final ConcurrentHashMap<Long, OnlineUser> onlineUsers = new ConcurrentHashMap<>();

    public static void addUser(OnlineUser user) {
        onlineUsers.put(user.getId(), user);
    }

    public static void removeUser(Long userId) {
        onlineUsers.remove(userId);
    }

    public static OnlineUser getUser(Long userId) {
        return onlineUsers.get(userId);
    }

    public static boolean isUserOnline(Long userId) {
        return onlineUsers.containsKey(userId);
    }

    public static ConcurrentHashMap<Long, OnlineUser> getAllUsers() {
        return onlineUsers;
    }

    public static int getTotalOnlineUsers() {
        return onlineUsers.size();
    }
}

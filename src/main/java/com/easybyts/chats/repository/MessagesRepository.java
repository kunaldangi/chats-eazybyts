package com.easybyts.chats.repository;

import com.easybyts.chats.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
// import java.util.Optional;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query("SELECT m FROM Messages m WHERE (m.senderId = :user1Id AND m.receiverId = :user2Id) OR (m.senderId = :user2Id AND m.receiverId = :user1Id) ORDER BY m.createdAt ASC")
    List<Messages> findChatBetweenUsersById(Long user1Id, Long user2Id);
}
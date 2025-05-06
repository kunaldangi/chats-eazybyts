package com.easybyts.chats.repository;

import com.easybyts.chats.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndUsername(String email, String username);
    Optional<User> findByEmailOrUsername(String email, String username);
    List<User> findByIdNot(Long id);
}

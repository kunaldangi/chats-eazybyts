package com.easybyts.chats.controller.Auth.Login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.easybyts.chats.model.User;
import com.easybyts.chats.repository.UserRepository;
import com.easybyts.chats.util.JwtUtil;
import com.easybyts.chats.util.PasswordEncrypt;

@Service
public class LoginRoute {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> handleRoute(LoginRequest request, HttpServletResponse response) {

        String username = request.getEmail(); // users can send username in email field as well
        String email = request.getEmail();
        System.out.println("Email: " + email + ", Username: " + username + ", Password: " + request.getPassword());
        
        if ((request.getEmail() == null || request.getEmail().isEmpty()) && (request.getPassword() == null || request.getPassword().isEmpty())) return ResponseEntity.badRequest().body(new LoginResponse("error", "Email or Username is required!"));

        Optional<User> existing = userRepository.findByEmailOrUsername(email, username);
        if (!existing.isPresent()) {
            return ResponseEntity.badRequest().body(new LoginResponse("error", "User not found!"));
        }

        try {
            User user = existing.get();
            if (!PasswordEncrypt.checkPassword(request.getPassword(), user.getPassword())) {
                return ResponseEntity.badRequest().body(new LoginResponse("error", "Invalid password!"));
            }

            Map<String, Object> tokenObj = new HashMap<>();
            tokenObj.put("id", user.getId());
            tokenObj.put("email", user.getEmail());
            tokenObj.put("username", user.getUsername());

            ObjectMapper mapper = new ObjectMapper();
            String tokenBody = mapper.writeValueAsString(tokenObj);

            String sessionToken = jwtUtil.generateToken(tokenBody, (60 * 24 * 7)); // 7 days token
            System.out.println("Session Token: " + sessionToken);

            Cookie cookie = new Cookie("session", sessionToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 24 * 7); // 7 days as our jwt token valid for 7 days as well

            response.addCookie(cookie);

            return ResponseEntity.ok(new LoginResponse("success", "Login successful!"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponse("error", "An error occurred!"));
        }
    }
}

package com.easybyts.chats.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
// import java.util.Base64;

@Component
public class JwtUtil {

    private String secret;

    public JwtUtil() {
        Dotenv dotenv = Dotenv.load();
        this.secret = dotenv.get("JWT_SESSION_SECRET");
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String subject, int expirationInMinutes) {
        int millliseconds = expirationInMinutes * 60 * 1000;
        return Jwts.builder().subject(subject).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + millliseconds)).signWith(getSigningKey()).compact();
    }

    public String validateTokenAndGetSubject(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        for (Cookie cookie : cookies) {
            if ("session".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}

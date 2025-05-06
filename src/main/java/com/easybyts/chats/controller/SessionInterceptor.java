package com.easybyts.chats.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.easybyts.chats.DTO.UserSessionData;
import com.easybyts.chats.config.RequestResponse;
import com.easybyts.chats.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public SessionInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwtUtil.getJwtFromCookies(request);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            
            RequestResponse requestResponse = new RequestResponse("error", "Unauthorized access!");
            String jsonResponse = new ObjectMapper().writeValueAsString(requestResponse);

            response.getWriter().write(jsonResponse);
        }

        try {
            String data = jwtUtil.validateTokenAndGetSubject(token);
            UserSessionData userData = new ObjectMapper().readValue(data, UserSessionData.class);
            request.setAttribute("user", userData); // get the data using userData.get("id")

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

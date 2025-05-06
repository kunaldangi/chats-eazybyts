package com.easybyts.chats.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.easybyts.chats.util.JwtUtil;

@Controller
public class FrontendController {

    private final JwtUtil jwtUtil;

    public FrontendController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public String handleRoot(HttpServletRequest request, HttpServletResponse response) {
        String token = jwtUtil.getJwtFromCookies(request);

        if (token == null) {
            return "redirect:/login";
        }

        try {
            String data = jwtUtil.validateTokenAndGetSubject(token);

            Map<String, Object> userData = new ObjectMapper().readValue(data, new TypeReference<Map<String, Object>>() {});
            request.setAttribute("user", userData); // get the data using userData.get("id")

            return "forward:/index.html";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }
    
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}

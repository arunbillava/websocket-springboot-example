package com.tech.spring.example.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.tech.spring.example.model.UserRequest;
import com.tech.spring.example.model.UserResponse;

@Controller
public class UserController {


    @MessageMapping("/user")
    @SendTo("/topic/user")
    public UserResponse getUser(UserRequest user) {

        return new UserResponse("Hi " + user.getName());
    }
}

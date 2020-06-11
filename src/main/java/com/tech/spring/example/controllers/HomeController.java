package com.tech.spring.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class HomeController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Welcome to websocket";
	}
}

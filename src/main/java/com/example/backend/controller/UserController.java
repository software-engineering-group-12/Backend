package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

}

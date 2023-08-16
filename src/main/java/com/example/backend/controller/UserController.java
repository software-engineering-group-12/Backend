package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) throws ExecutionException, InterruptedException {
		return userService.addUser(user);
	}

	@GetMapping()
	public User getUserByUserName(@RequestParam String userName) throws ExecutionException, InterruptedException {
		return userService.getUserByUserName(userName);
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) throws ExecutionException, InterruptedException {
		return userService.login(userName, password);
	}


}

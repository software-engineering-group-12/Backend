package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<String> addUser(User user) throws ExecutionException, InterruptedException {
		if(!EmailValidator.getInstance().isValid(user.getEmail())) {
			return ResponseEntity.badRequest().body("Invalid email");
		}
		if(userRepository.getUserByUserName(user.getUsername()) != null) {
			return ResponseEntity.badRequest().body("Username is already taken");
		}
		userRepository.addUser(user);
		return ResponseEntity.ok("User added");
	}

	public User getUserByUserName(String userName) throws ExecutionException, InterruptedException {
		return userRepository.getUserByUserName(userName);
	}

	public User getUserById(String id) throws ExecutionException, InterruptedException {
		return userRepository.getUserById(id);
	}

	public ResponseEntity<String> login(String username, String password) throws ExecutionException, InterruptedException {
		User user = userRepository.getUserByUserName(username);
		if(user == null) {
			return ResponseEntity.badRequest().body("User name does not exist");
		}
		if(user.getPassword().equals(password)) {
			return ResponseEntity.ok(user.getId());
		}
		else {
			return ResponseEntity.badRequest().body("Invalid password");
		}
	}

}

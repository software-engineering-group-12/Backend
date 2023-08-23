package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.google.firebase.auth.hash.Bcrypt;
import org.apache.commons.validator.routines.EmailValidator;
import org.mindrot.jbcrypt.BCrypt;
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
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		String id = userRepository.addUser(user);
		return ResponseEntity.ok(id);
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
		if(BCrypt.checkpw(password, user.getPassword())) {
			return ResponseEntity.ok(user.getId());
		}
		else {
			return ResponseEntity.badRequest().body("Invalid password");
		}
	}

}

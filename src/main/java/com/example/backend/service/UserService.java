package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void addUser(User user) {
		userRepository.addUser(user);
	}

	public User getUserByUserName(String userName) throws ExecutionException, InterruptedException {
		return userRepository.getUserByUserName(userName);
	}

	public User getUserById(String id) throws ExecutionException, InterruptedException {
		return userRepository.getUserById(id);
	}

}

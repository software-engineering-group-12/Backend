package com.example.backend.repository;

import com.example.backend.model.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {

	void addUser(User user);

	User getUserByUserName(String userName) throws ExecutionException, InterruptedException;
	User getUserById(String id) throws ExecutionException, InterruptedException;


}

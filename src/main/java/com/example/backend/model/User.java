package com.example.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

public class User {

	@DocumentId
	private String id;

	private String username;

	private String password;

	private String email;

	private String mobileNo;


	public User(String name) {
		this.username = name;
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}

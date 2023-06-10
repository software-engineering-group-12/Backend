package com.example.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

public class User {

	@DocumentId
	private String id;

	private String name;

	public User(String name) {
		this.name = name;
	}

	public User() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

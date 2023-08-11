package com.example.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.List;

public class Location {

	@DocumentId
	private String id;

	private String name;

	private String description;

	private List<String> imageUrls;

	public Location(){}

	public Location(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Location(String name, String description, List<String> imageUrls) {
		this.name = name;
		this.description = description;
		this.imageUrls = imageUrls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
}

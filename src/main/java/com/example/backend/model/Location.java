package com.example.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Location {

	@DocumentId
	private String id;

	private String name;

	private String description;

	private List<String> imageUrls = new ArrayList<>();
	private MultipartFile image;

	private String location;

	public Location(){
		id = UUID.randomUUID().toString();
	}

	public Location(String name, String description) {
		id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
	}

	public Location(String name, String description, List<String> imageUrls) {
		id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.imageUrls = imageUrls;
	}

	public Location(String name, String description, List<String> imageUrls, MultipartFile image) {
		id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.imageUrls = imageUrls;
		this.image = image;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

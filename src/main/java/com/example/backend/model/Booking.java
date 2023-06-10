package com.example.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

public class Booking {

	@DocumentId
	private String id;

	private Slot timeSlot;

	private User user;

	private Location location;

	public Booking(Slot timeSlot, User user, Location location) {
		this.timeSlot = timeSlot;
		this.user = user;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Slot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(Slot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}

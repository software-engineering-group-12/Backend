package com.example.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.Date;

public class Booking {

	@DocumentId
	private String id;

	private Slot timeSlot;

	private String user;

	private String location;

	private Date startTime;

	private Date endTime;


	public Booking() {
	}

	public Booking(Slot timeSlot, String user, String location) {
		this.timeSlot = timeSlot;
		this.user = user;
		this.location = location;
	}

	public Booking(String user, String location, Date startTime, Date endTime) {
		this.user = user;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}

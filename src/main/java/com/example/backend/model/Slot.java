package com.example.backend.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Slot {

	private Date startTime;

	private Date endTime;

	public Slot(Date startTIme, Date endTIme) {
		this.startTime = startTIme;
		this.endTime = endTIme;
	}

	public Date getStartTIme() {
		return startTime;
	}

	public void setStartTIme(Date startTIme) {
		this.startTime = startTIme;
	}

	public Date getEndTIme() {
		return endTime;
	}

	public void setEndTIme(Date endTIme) {
		this.endTime = endTIme;
	}

}

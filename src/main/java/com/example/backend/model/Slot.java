package com.example.backend.model;

import java.time.LocalDateTime;

public class Slot {

	private LocalDateTime startTIme;

	private LocalDateTime endTIme;

	public Slot(LocalDateTime startTIme, LocalDateTime endTIme) {
		this.startTIme = startTIme;
		this.endTIme = endTIme;
	}

	public LocalDateTime getStartTIme() {
		return startTIme;
	}

	public void setStartTIme(LocalDateTime startTIme) {
		this.startTIme = startTIme;
	}

	public LocalDateTime getEndTIme() {
		return endTIme;
	}

	public void setEndTIme(LocalDateTime endTIme) {
		this.endTIme = endTIme;
	}

}

package com.example.backend.service;

import com.example.backend.model.Booking;
import com.example.backend.model.Location;
import com.example.backend.model.Slot;
import com.example.backend.model.User;
import com.example.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;

	public ResponseEntity<String> createBooking(String userId, String locationID, Date startTime, Date endTime) throws ExecutionException, InterruptedException {
		if(endTime.before(startTime)) {
			return ResponseEntity.badRequest().body("End time is before start time");
		}
		Location location = locationService.getLocation(locationID);
		if(location == null) {
			return ResponseEntity.badRequest().body("Location does not exist");
		}
		User user = userService.getUserById(userId);
		if(user == null) {
			return ResponseEntity.badRequest().body("User does not exist");
		}
		Slot slot = new Slot(startTime, endTime);
		if(isBookingSlotAvailable(location, slot)) {
			bookingRepository.addBooking(new Booking(userId, locationID, startTime, endTime));
			return ResponseEntity.ok("Booking crated");
		}
		else {
			return ResponseEntity.badRequest().body("Time slot already taken");
		}
	}

	public ResponseEntity<String> createBooking(Booking booking) throws ExecutionException, InterruptedException {
		return createBooking(booking.getUser(), booking.getLocation(), booking.getStartTime(), booking.getEndTime());
	}

	public List<Booking> getBookingByUserId(String userId) throws ExecutionException, InterruptedException {
		 return bookingRepository.getBookingsByUserId(userId);
	}


	public boolean isBookingSlotAvailable(Location location, Slot slot) throws ExecutionException, InterruptedException {
		List<Booking> locationBookings = bookingRepository.getBookingsByLocation(location);
		for(Booking booking: locationBookings) {
			if(slot.getStartTIme().before(booking.getEndTime()) && booking.getStartTime().before(slot.getEndTIme())) {
				return false;
			}
		}
		return true;
	}

}

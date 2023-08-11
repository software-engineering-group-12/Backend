
package com.example.backend.repository;

import com.example.backend.model.Booking;
import com.example.backend.model.Location;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BookingRepository {

    List<Booking> getBooking(List<String> bookings) throws ExecutionException, InterruptedException;

    void addBooking(Booking booking);

    List<Booking> getBookingsByLocation(Location location) throws ExecutionException, InterruptedException;
    

}


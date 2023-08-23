package com.example.backend;

import com.example.backend.controller.BookingController;
import com.example.backend.model.Booking;
import com.example.backend.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    private BookingController bookingController;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingService = mock(BookingService.class);
        bookingController = mock(BookingController.class);
    }

    @Test
    void testCreateBooking() throws ExecutionException, InterruptedException {
        Booking booking = new Booking(); // Set up a Booking object

        when(bookingService.createBooking(any())).thenReturn(ResponseEntity.ok("Booking created"));

        ResponseEntity<String> response = bookingController.createBooking(booking);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking created", response.getBody());

        verify(bookingService, times(1)).createBooking(booking);
    }

    @Test
    void testGetUserBooking() throws ExecutionException, InterruptedException {
        String userId = "user123";

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingService.getBookingByUserId(userId)).thenReturn(bookings);

        List<Booking> response = bookingController.getUserBooking(userId);

        assertEquals(2, response.size());

        verify(bookingService, times(1)).getBookingByUserId(userId);
    }
}

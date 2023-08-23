package com.example.backend;

import com.example.backend.model.Booking;
import com.example.backend.model.Location;
import com.example.backend.model.Slot;
import com.example.backend.model.User;
import com.example.backend.repository.BookingRepository;
import com.example.backend.service.BookingService;
import com.example.backend.service.LocationService;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private LocationService locationService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBookingValid() throws ExecutionException, InterruptedException {
        String userId = "user123";
        String locationId = "location123";
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + 3600000); // Adding 1 hour

        Location location = new Location();
        User user = new User(userId);

        when(locationService.getLocation(locationId)).thenReturn(location);
        when(userService.getUserById(userId)).thenReturn(user);
        when(bookingRepository.getBookingsByLocation(location)).thenReturn(new ArrayList<>());

        ResponseEntity<String> response = bookingService.createBooking(userId, locationId, startTime, endTime);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Booking crated", response.getBody());

        verify(bookingRepository, times(1)).addBooking(any(Booking.class));
    }

    @Test
    void testCreateBookingInvalidLocation() throws ExecutionException, InterruptedException {
        String userId = "user123";
        String locationId = "invalidLocation";
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + 3600000); // Adding 1 hour

        when(locationService.getLocation(locationId)).thenReturn(null);

        ResponseEntity<String> response = bookingService.createBooking(userId, locationId, startTime, endTime);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Location does not exist", response.getBody());

        verify(bookingRepository, never()).addBooking(any(Booking.class));
    }


    @Test
    void testIsBookingSlotAvailableWhenNotAvailable() throws ExecutionException, InterruptedException {
        Location location = new Location();

        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + 3600000); // Adding 1 hour

        Booking existingBooking = new Booking("user456", "location123", new Date(startTime.getTime() + 3000000), new Date(startTime.getTime() + 5400000));
        List<Booking> locationBookings = new ArrayList<>();
        locationBookings.add(existingBooking);

        when(bookingRepository.getBookingsByLocation(location)).thenReturn(locationBookings);

        Slot slot = new Slot(startTime, endTime);

        boolean isAvailable = bookingService.isBookingSlotAvailable(location, slot);

        assertFalse(isAvailable);

        verify(bookingRepository, times(1)).getBookingsByLocation(location);
    }
}

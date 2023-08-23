package com.example.backend;

import com.example.backend.controller.LocationController;
import com.example.backend.model.Location;
import com.example.backend.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddLocation() {
        Location locationToAdd = new Location();

        when(locationService.addLocation(any(Location.class))).thenReturn(ResponseEntity.ok("Location added"));

        ResponseEntity<String> response = locationController.addLocation(locationToAdd);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Location added", response.getBody());

        verify(locationService, times(1)).addLocation(locationToAdd);
    }

    @Test
    void testGetLocations() throws ExecutionException, InterruptedException {
        List<Location> expectedLocations = new ArrayList<>();
        expectedLocations.add(new Location());
        expectedLocations.add(new Location());

        when(locationService.getLocations()).thenReturn(expectedLocations);

        List<Location> resultLocations = locationController.getLocations();

        assertEquals(expectedLocations.size(), resultLocations.size());
        assertEquals(expectedLocations, resultLocations);

        verify(locationService, times(1)).getLocations();
    }

    @Test
    void testGetLocationById() throws ExecutionException, InterruptedException {
        Location expectedLocation = new Location();
        String locationId = expectedLocation.getId();

        when(locationService.getLocation(locationId)).thenReturn(expectedLocation);

        Location resultLocation = locationController.getLocationById(locationId);

        assertEquals(expectedLocation, resultLocation);

        verify(locationService, times(1)).getLocation(locationId);
    }
}

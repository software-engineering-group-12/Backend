package com.example.backend;

import com.example.backend.model.Location;
import com.example.backend.repository.LocationRepository;
import com.example.backend.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceTests {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLocations() throws ExecutionException, InterruptedException {
        List<Location> expectedLocations = new ArrayList<>();
        expectedLocations.add(new Location());
        expectedLocations.add(new Location());

        when(locationRepository.getLocations()).thenReturn(expectedLocations);

        List<Location> resultLocations = locationService.getLocations();

        assertEquals(expectedLocations.size(), resultLocations.size());
        assertEquals(expectedLocations, resultLocations);

        verify(locationRepository, times(1)).getLocations();
    }
}

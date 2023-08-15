package com.example.backend.controller;

import com.example.backend.model.Location;
import com.example.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<String> addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @GetMapping
    public List<Location> getLocations() throws ExecutionException, InterruptedException {
        return locationService.getLocations();
    }

}

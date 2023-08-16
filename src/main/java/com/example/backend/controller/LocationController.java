package com.example.backend.controller;

import com.example.backend.model.Location;
import com.example.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@CrossOrigin
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<String> addLocation(@ModelAttribute Location location) {
        return locationService.addLocation(location);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Location> getLocations() throws ExecutionException, InterruptedException {
        return locationService.getLocations();
    }

    @GetMapping
    public Location getLocationById(@RequestParam("id") String id) throws ExecutionException, InterruptedException {
        return locationService.getLocation(id);
    }

}

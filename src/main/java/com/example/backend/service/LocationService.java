package com.example.backend.service;

import com.example.backend.model.Location;
import com.example.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public Location getLocation(String id) throws ExecutionException, InterruptedException {
		return locationRepository.getLocation(id);
	}

	public ResponseEntity<String> addLocation(Location location) {
		try {
			locationRepository.addLocation(location);
			return ResponseEntity.ok().body("Location added");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Adding location failed");
		}
	}

	public List<Location> getLocations() throws ExecutionException, InterruptedException {
		return locationRepository.getLocations();
	}

}

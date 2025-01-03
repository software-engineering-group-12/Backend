package com.example.backend.repository;

import com.example.backend.model.Location;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LocationRepository {

    Location getLocation(String id) throws ExecutionException, InterruptedException;

    void addLocation(String name, String description, List<String> imageUrl);

    void addLocation(Location location);

    List<Location> getLocations() throws ExecutionException, InterruptedException;


}

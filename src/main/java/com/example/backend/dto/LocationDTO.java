package com.example.backend.dto;

import com.example.backend.model.Location;
import com.google.cloud.firestore.annotation.DocumentId;
import org.modelmapper.ModelMapper;

import java.util.List;

public class LocationDTO {
    @DocumentId
    private String id;

    private String name;

    private String description;

    private List<String> imageUrls;

    private String location;

    public LocationDTO(){}

    public LocationDTO(String id, String name, String description, List<String> imageUrls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    public static LocationDTO convertToDTO(Location location) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(location, LocationDTO.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

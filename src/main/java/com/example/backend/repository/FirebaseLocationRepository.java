package com.example.backend.repository;

import com.example.backend.Utill.Constants;
import com.example.backend.model.Location;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseLocationRepository implements LocationRepository {

    @Override
    public Location getLocation(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentSnapshot documentSnapshot = firestore.collection(Constants.LOCATION_DATABASE).document(id).get().get();
        Location location = null;
        if(documentSnapshot.exists()) {
            location = documentSnapshot.toObject(Location.class);
        }
        return location;
    }

    @Override
    public void addLocation(String name, String description, List<String> imageUrl) {
        addLocation(new Location(name, description, imageUrl));
    }

    @Override
    public void addLocation(Location location) {
        FirestoreClient.getFirestore().collection(Constants.LOCATION_DATABASE).add(location);
    }
}

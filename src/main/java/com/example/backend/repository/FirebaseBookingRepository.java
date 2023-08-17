package com.example.backend.repository;

import com.example.backend.Utill.Constants;
import com.example.backend.model.Booking;
import com.example.backend.model.Location;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseBookingRepository implements BookingRepository {

    @Override
    public List<Booking> getBooking(List<String> bookingIds) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> queryDocumentSnapshots = firestore.collection(Constants.BOOKING_DATABASE).whereIn("user", bookingIds).get().get().getDocuments();
        List<Booking> bookings = new ArrayList<>();
        for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
            bookings.add(documentSnapshot.toObject(Booking.class));
        }
        return bookings;
    }

    @Override
    public void addBooking(Booking booking) {
        Firestore fireStore = FirestoreClient.getFirestore();
        fireStore.collection(Constants.BOOKING_DATABASE).add(booking);
    }

    @Override
    public List<Booking> getBookingsByLocation(Location location) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documents = firestore.collection(Constants.BOOKING_DATABASE).whereEqualTo("location", location.getId()).get().get().getDocuments();
        List<Booking> bookings = new ArrayList<>();
        for (QueryDocumentSnapshot documentSnapshot: documents) {
            bookings.add(documentSnapshot.toObject(Booking.class));
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByUserId(String userId) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documents = firestore.collection(Constants.BOOKING_DATABASE).whereEqualTo("user", userId).get().get().getDocuments();
        List<Booking> bookings = new ArrayList<>();
        for (QueryDocumentSnapshot documentSnapshot: documents) {
            bookings.add(documentSnapshot.toObject(Booking.class));
        }
        return bookings;
    }
}

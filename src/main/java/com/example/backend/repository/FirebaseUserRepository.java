package com.example.backend.repository;

import com.example.backend.model.User;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class FirebaseUserRepository implements UserRepository {

	@Override
	public void addUser(User user) {
		Firestore fireStore = FirestoreClient.getFirestore();
		fireStore.collection("user").add(user);
	}
}

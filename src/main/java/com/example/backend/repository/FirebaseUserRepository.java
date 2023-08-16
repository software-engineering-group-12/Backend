package com.example.backend.repository;

import com.example.backend.Utill.Constants;
import com.example.backend.model.Location;
import com.example.backend.model.User;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseUserRepository implements UserRepository {

	@Override
	public void addUser(User user) {
		Firestore fireStore = FirestoreClient.getFirestore();
		fireStore.collection(Constants.USER_DATABASE).add(user);
	}

	@Override
	public User getUserByUserName(String userName) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		List<QueryDocumentSnapshot> documents = firestore.collection(Constants.USER_DATABASE).whereEqualTo("username", userName).get().get().getDocuments();
		for (DocumentSnapshot documentSnapshot: documents) {
			return documentSnapshot.toObject(User.class);
		}
		return null;
	}

	@Override
	public User getUserById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot documentSnapshot = firestore.collection(Constants.USER_DATABASE).document(id).get().get();
		User user = null;
		if(documentSnapshot.exists()) {
			user = documentSnapshot.toObject(User.class);
		}
		return user;
	}
}

package com.example.backend.firebase;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitializer {

	@PostConstruct
	public void initialize() {
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("se-group-12-firebase-adminsdk-ko5pb-7b9c469893.json");
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setStorageBucket("se-group-12.appspot.com")
					.build();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		FirebaseApp.initializeApp(options);
	}

}

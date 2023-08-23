package com.example.backend;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUserValidEmail() throws ExecutionException, InterruptedException {
        User userToAdd = new User("username", "email@example.com", "password");

        when(userRepository.getUserByUserName(userToAdd.getUsername())).thenReturn(null);
        when(userRepository.addUser(userToAdd)).thenReturn("user123");

        ResponseEntity<String> response = userService.addUser(userToAdd);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("user123", response.getBody());

        verify(userRepository, times(1)).addUser(userToAdd);
    }

    @Test
    void testAddUserInvalidEmail() throws ExecutionException, InterruptedException {
        User userToAdd = new User("username", "invalidemail", "password");

        ResponseEntity<String> response = userService.addUser(userToAdd);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid email", response.getBody());

        verify(userRepository, never()).addUser(userToAdd);
    }

    @Test
    void testAddUserUsernameTaken() throws ExecutionException, InterruptedException {
        User existingUser = new User("existinguser", "existing@example.com", "password");
        User userToAdd = new User("existinguser", "newuser@example.com", "password");

        when(userRepository.getUserByUserName(existingUser.getUsername())).thenReturn(existingUser);

        ResponseEntity<String> response = userService.addUser(userToAdd);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Username is already taken", response.getBody());

        verify(userRepository, never()).addUser(userToAdd);
    }

    // Similar tests for getUserByUserName, getUserById, and login can be written
}

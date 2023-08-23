package com.example.backend;

import com.example.backend.controller.UserController;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() throws ExecutionException, InterruptedException {
        User userToAdd = new User("username", "email@example.com", "password");

        when(userService.addUser(userToAdd)).thenReturn(ResponseEntity.ok("User added"));

        ResponseEntity<String> response = userController.addUser(userToAdd);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User added", response.getBody());

        verify(userService, times(1)).addUser(userToAdd);
    }

    @Test
    void testGetUserByUserName() throws ExecutionException, InterruptedException {
        String userName = "username";
        User expectedUser = new User(userName, "email@example.com", "password");

        when(userService.getUserByUserName(userName)).thenReturn(expectedUser);

        User resultUser = userController.getUserByUserName(userName);

        assertEquals(expectedUser, resultUser);

        verify(userService, times(1)).getUserByUserName(userName);
    }

    @Test
    void testGetUserById() throws ExecutionException, InterruptedException {
        String userId = "user123";
        User expectedUser = new User("username", "email@example.com", "password");

        when(userService.getUserById(userId)).thenReturn(expectedUser);

        User resultUser = userController.getUserById(userId);

        assertEquals(expectedUser, resultUser);

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testLoginSuccess() throws ExecutionException, InterruptedException {
        String userName = "username";
        String password = "password";
        User user = new User(userName, "email@example.com", password);

        when(userService.login(userName, password)).thenReturn(ResponseEntity.ok("Logged in successfully"));

        ResponseEntity<String> response = userController.login(userName, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Logged in successfully", response.getBody());

        verify(userService, times(1)).login(userName, password);
    }

    @Test
    void testLoginInvalidUsername() throws ExecutionException, InterruptedException {
        String userName = "nonexistentuser";
        String password = "password";

        when(userService.login(userName, password)).thenReturn(ResponseEntity.badRequest().body("User name does not exist"));

        ResponseEntity<String> response = userController.login(userName, password);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User name does not exist", response.getBody());

        verify(userService, times(1)).login(userName, password);
    }

    @Test
    void testLoginInvalidPassword() throws ExecutionException, InterruptedException {
        String userName = "username";
        String password = "incorrectpassword";
        User user = new User(userName, "email@example.com", "password");

        when(userService.login(userName, password)).thenReturn(ResponseEntity.badRequest().body("Invalid password"));

        ResponseEntity<String> response = userController.login(userName, password);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid password", response.getBody());

        verify(userService, times(1)).login(userName, password);
    }
}

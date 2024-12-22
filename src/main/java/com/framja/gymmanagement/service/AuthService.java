package com.framja.gymmanagement.service;

import com.framja.gymmanagement.model.*;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final Map<String, User> userDatabase = new HashMap<>();
    private static User currentUser; // Variable to keep track of the current user

    static {
        // Initializing the user database with some sample users
        // Adjusted usernames to be unique keys in the map
        userDatabase.put("member1", new Member("member1", "member123", "123"));
        userDatabase.put("member2", new Member("member2", "member123", "123"));
        userDatabase.put("trainer1", new Trainer("trainer1", "trainer123", "123"));
        // Uncomment and adjust the following lines if Admin and Manager classes are defined
        // userDatabase.put("admin", new Admin("admin", "admin123", Role.ADMIN));
        // userDatabase.put("manager", new GymManager("manager", "manager123", Role.GYMMANAGER));
    }

    // Login function
    public static User login(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user; // Set the current user upon successful login
            return user;
        }
        return null;
    }

    // Register function
    public static boolean register(User newUser) {
        String username = newUser.getName();
        if (!userDatabase.containsKey(username)) {
            userDatabase.put(username, newUser);
            return true; // Registration successful
        } else {
            return false; // Username already exists
        }
    }

    // Logout function
    public static void logout() {
        currentUser = null;
    }

    // Getter for currentUser
    public static User getCurrentUser() {
        return currentUser;
    }
}

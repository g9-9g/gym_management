package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    public UserServiceImpl() {
        // Add default users
        users.add(new User("john_doe", "password123", "Member"));
        users.add(new User("jane_smith", "password456", "Trainer"));
        users.add(new User("admin_user", "adminpass", "Admin"));
        users.add(new User("member_user", "memberpass", "Member"));
        users.add(new User("trainer_user", "trainerpass", "Trainer"));
    }

    @Override
    public void addUser(User user) {
        if (users.stream().anyMatch(existingUser -> existingUser.getUsername().equals(user.getUsername()))) {
            throw new IllegalArgumentException("User with username " + user.getUsername() + " already exists.");
        }
        users.add(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return users.stream().filter(user -> (user.getUsername().equals(username) && user.getPassword().equals(password))).findFirst();
    }

    @Override
    public boolean removeUser(String username) {
        return users.removeIf(user -> user.getUsername().equals(username));
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public List<User> findAllUsersByRole(String role) {
        return users.stream()
                .filter(user -> user.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }
}

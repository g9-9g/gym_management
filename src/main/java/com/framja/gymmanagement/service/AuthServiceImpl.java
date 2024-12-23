package com.framja.gymmanagement.service;

import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.AuthService;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.utils.ServiceContainer;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {
    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(String username, String password) {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }

        return null;
    }

    @Override
    public User register(String username, String password, RoleType role) {
        if (userService.findUserByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Error: Username already exists.");
        }

        User newUser = new User(username, password, role);
        userService.addUser(newUser);
        return newUser;
    }
}

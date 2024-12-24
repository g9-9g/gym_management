package com.framja.gymmanagement.service;

import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Admin;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;
import com.framja.gymmanagement.utils.ServiceContainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

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
                .filter(user -> user.getRole().name().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }
}

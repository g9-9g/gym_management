package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Optional<User> findUserByUsername(String username);
    boolean removeUser(String username);
    List<User> findAllUsers();
    List<User> findAllUsersByRole(String role);
}


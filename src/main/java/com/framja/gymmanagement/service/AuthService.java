package com.framja.gymmanagement.service;

import com.framja.gymmanagement.model.*;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final Map<String, User> userDatabase = new HashMap<>();

    static {
        // userDatabase.put("admin", new Admin("admin", "admin123", Role.ADMIN));
        // userDatabase.put("manager", new User("manager", "manager123", Role.GYMMANAGER));
        userDatabase.put("member", new Member("member", "member123", "123"));
        userDatabase.put("member", new Member("member2", "member123", "123"));

        userDatabase.put("trainer", new Trainer("trainer1", "trainer123", "123"));

    }

    public static User login(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

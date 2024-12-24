package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.model.User;

import java.time.LocalDateTime;

public class SessionManager {
    private static SessionManager instance;
    private User CurrentUser;
    private LocalDateTime loginTime;

    private SessionManager() {
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.CurrentUser = currentUser;
        this.loginTime = LocalDateTime.now();
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void clearSession() {
        this.CurrentUser = null;
        this.loginTime = null;
    }

    public boolean isLoggedIn() {
        return CurrentUser != null;
    }
}

package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.interfaces.Role;

import java.time.LocalDateTime;

public class SessionManager {
    private static SessionManager instance;
    private Role currentRole;
    private LocalDateTime loginTime;

    private SessionManager() {
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
        this.loginTime = LocalDateTime.now();
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void clearSession() {
        this.currentRole = null;
        this.loginTime = null;
    }

    public boolean isLoggedIn() {
        return currentRole != null;
    }
}

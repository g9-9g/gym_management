package com.framja.gymmanagement.role;

import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.model.User;

public class Admin extends User {

    public Admin(String username, String password, RoleType role, String phoneNumber, String gender, String address) {
        super(username, password, role, phoneNumber, gender, address);
    }

    public Admin(String username, String password, RoleType role) {
        super(username, password, role);
    }
}

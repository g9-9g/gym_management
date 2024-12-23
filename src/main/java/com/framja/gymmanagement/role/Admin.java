package com.framja.gymmanagement.role;

import com.framja.gymmanagement.model.BaseRole;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class Admin extends BaseRole {
    public Admin(User self) {
        super(self);
    }

    @Override
    protected List<MenuOption<?>> defineMenuOptions() {
        return null;
    }
}

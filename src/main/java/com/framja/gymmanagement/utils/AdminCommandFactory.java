package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.interfaces.CommandFactory;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class AdminCommandFactory implements CommandFactory {
    private final User user;

    public AdminCommandFactory(User user) {
        this.user = user;
    }


    @Override
    public List<MenuOption<?>> createMenuOptions() {
        return null;
    }
}

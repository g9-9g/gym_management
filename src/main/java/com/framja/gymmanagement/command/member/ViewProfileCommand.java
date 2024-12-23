package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.model.User;

public class ViewProfileCommand implements ActionCommand<User> {
    private final User user;

    public ViewProfileCommand(User user) {
        this.user = user;
    }

    @Override
    public User execute() {
        return user;
    }
}

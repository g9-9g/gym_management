package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.User;

import java.util.Optional;

public class FindUserByUsernameCommand implements ActionCommand<Optional<User>> {
    private final UserService userService;
    private final String username;

    public FindUserByUsernameCommand(UserService userService, String username) {
        this.userService = userService;
        this.username = username;
    }

    @Override
    public Optional<User> execute() {
        return userService.findUserByUsername(username);
    }
}


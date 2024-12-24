package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.User;

public class AddUserCommand implements ActionCommand<Boolean> {
    private final UserService userService;
    private final User user;

    public AddUserCommand(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    @Override
    public Boolean execute() {
        userService.addUser(user);
        return true;
    }
}

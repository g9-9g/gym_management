package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;

public class RemoveUserCommand implements ActionCommand<Boolean> {
    private final UserService userService;
    private final String username;

    public RemoveUserCommand(UserService userService, String username) {
        this.userService = userService;
        this.username = username;
    }

    @Override
    public Boolean execute() {
        return userService.removeUser(username);
    }
}

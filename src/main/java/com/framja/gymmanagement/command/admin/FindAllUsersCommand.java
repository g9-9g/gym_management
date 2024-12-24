package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class FindAllUsersCommand implements ActionCommand<List<User>> {
    private final UserService userService;

    public FindAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> execute() {
        return userService.findAllUsers();
    }
}

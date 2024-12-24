package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.model.User;

public class UpdateProfileCommand implements ActionCommand<Boolean> {
    private final User user;
    private final String newUsername;
    private final String newPassword;

    public UpdateProfileCommand(User user, String newUsername, String newPassword) {
        this.user = user;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }

    @Override
    public Boolean execute() {
//        user.setUsername(newUsername);
        user.setPassword(newPassword);
        return true;
    }
}

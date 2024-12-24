package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.role.Trainer;

public class UpdateTrainerCommand implements ActionCommand<Boolean> {
    private final UserService userService;
    private final Trainer updatedTrainer;

    public UpdateTrainerCommand(UserService userService, Trainer updatedTrainer) {
        this.userService = userService;
        this.updatedTrainer = updatedTrainer;
    }

    @Override
    public Boolean execute() {
        return userService.updateTrainer(updatedTrainer);
    }
}

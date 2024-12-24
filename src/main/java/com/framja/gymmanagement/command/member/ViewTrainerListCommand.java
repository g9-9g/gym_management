package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.role.Trainer;

import java.util.List;

public class ViewTrainerListCommand implements ActionCommand<List<Trainer>> {
    private final UserService userService;

    public ViewTrainerListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Trainer> execute() {
        // Trả về danh sách các trainer
        return userService.getAllUsersByRole(Trainer.class); // Giả sử UserService có phương thức này
    }
}

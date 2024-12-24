package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;

public class CreateGymClassCommand implements ActionCommand<Boolean> {
    private final ClassService classService;
    private final GymClass gymClass;

    public CreateGymClassCommand(ClassService classService, GymClass gymClass) {
        this.classService = classService;
        this.gymClass = gymClass;
    }

    @Override
    public Boolean execute() {
        classService.createGymClass(gymClass);
        return true;
    }
}

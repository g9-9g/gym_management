package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;

import java.util.List;

public class ReadGymClassCommand implements ActionCommand<List<GymClass>> {
    private final ClassService classService;

    public ReadGymClassCommand(ClassService classService) {
        this.classService = classService;
    }

    @Override
    public List<GymClass> execute() {
        return classService.getAllClasses();
    }
}

package com.framja.gymmanagement.command.trainer;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class ViewTrainerClassCommand implements ActionCommand<List<GymClass>> {
    private final User self;
    private final ClassService classService;

    public ViewTrainerClassCommand(User self, ClassService classService) {
        this.self = self;
        this.classService = classService;
    }

    @Override
    public List<GymClass> execute() {
        return classService.getAllClasses().stream()
                .filter(gymClass -> gymClass.getInstructor().equals(self))
                .toList();
    }
}

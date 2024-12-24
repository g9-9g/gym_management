package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;

public class UpdateGymClassCommand implements ActionCommand<Boolean> {
    private final ClassService classService;
    private final int classId;
    private final GymClass updatedGymClass;

    public UpdateGymClassCommand(ClassService classService, int classId, GymClass updatedGymClass) {
        this.classService = classService;
        this.classId = classId;
        this.updatedGymClass = updatedGymClass;
    }

    @Override
    public Boolean execute() {
        classService.updateGymClass(classId, updatedGymClass);
        return true;
    }
}

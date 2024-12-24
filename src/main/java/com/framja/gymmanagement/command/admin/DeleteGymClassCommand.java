package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;

public class DeleteGymClassCommand implements ActionCommand<Boolean> {
    private final ClassService classService;
    private final int classId;

    public DeleteGymClassCommand(ClassService classService, int classId) {
        this.classService = classService;
        this.classId = classId;
    }

    @Override
    public Boolean execute() {
        classService.deleteGymClass(classId);
        return true;
    }
}

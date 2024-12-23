package com.framja.gymmanagement.command.trainer;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;
import com.framja.gymmanagement.model.User;

public class AssignTrainerCommand implements ActionCommand<Boolean> {
    private final User trainer; // Trainer cần đăng ký
    private final int gymClassId; // ID của GymClass
    private final ClassService classService;

    public AssignTrainerCommand(User trainer, int gymClassId, ClassService classService) {
//        if (!"Trainer".equals(trainer.getRole())) {
//            throw new IllegalArgumentException("User must be a Trainer.");
//        }
        this.trainer = trainer;
        this.gymClassId = gymClassId;
        this.classService = classService;
    }

    @Override
    public Boolean execute() {
        GymClass gymClass = classService.getClassById(gymClassId);
        if (gymClass.getInstructor() != null) {
            throw new IllegalStateException("GymClass already has an assigned instructor.");
        }
        gymClass.setInstructor(trainer);
        classService.updateGymClass(gymClassId, gymClass);
        return true;
    }
}


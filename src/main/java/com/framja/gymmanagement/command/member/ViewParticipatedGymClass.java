package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class ViewParticipatedGymClass implements ActionCommand<List<GymClass>> {
    private final User user;
    private final ClassService classService;

    public ViewParticipatedGymClass(User user, ClassService classService) {
        this.user = user;
        this.classService = classService;
    }

    @Override
    public List<GymClass> execute() {
        return classService.getAllClasses().stream()
                .filter(gymClass -> gymClass.getMembers().contains(user))
                .toList();
    }
}

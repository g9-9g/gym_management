package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;

import java.util.List;

public class ViewGymClassFromCourseCommand implements ActionCommand<List<GymClass>> {
    private final ClassService classService;
    private final int courseId;

    public ViewGymClassFromCourseCommand(ClassService classService, int courseId) {
        this.classService = classService;
        this.courseId = courseId;
    }

    @Override
    public List<GymClass> execute() {
        return classService.getClassesByCourseId(courseId);
    }
}

package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.Course;

public class UpdateCourseCommand implements ActionCommand<Boolean> {
    private final CourseService courseService;
    private final Course updatedCourse;

    public UpdateCourseCommand(CourseService courseService, Course updatedCourse) {
        this.courseService = courseService;
        this.updatedCourse = updatedCourse;
    }

    @Override
    public Boolean execute() {
        courseService.updateCourse(updatedCourse);
        return true;
    }
}

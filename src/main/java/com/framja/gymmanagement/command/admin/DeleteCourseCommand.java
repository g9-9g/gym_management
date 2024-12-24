package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.CourseService;

public class DeleteCourseCommand implements ActionCommand<Boolean> {
    private final CourseService courseService;
    private final int courseId;

    public DeleteCourseCommand(CourseService courseService, int courseId) {
        this.courseService = courseService;
        this.courseId = courseId;
    }

    @Override
    public Boolean execute() {
        return courseService.deleteCourse(courseId);
    }
}

package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.Course;

public class CreateCourseCommand implements ActionCommand<Boolean> {
    private final CourseService courseService;
    private final Course course;

    public CreateCourseCommand(CourseService courseService, Course course) {
        this.courseService = courseService;
        this.course = course;
    }

    @Override
    public Boolean execute() {
        courseService.createCourse(course);
        return true;
    }
}

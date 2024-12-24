package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.Course;

import java.util.List;

public class ReadAllCoursesCommand implements ActionCommand<List<Course>> {
    private final CourseService courseService;

    public ReadAllCoursesCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public List<Course> execute() {
        return courseService.findAllCourses();
    }
}

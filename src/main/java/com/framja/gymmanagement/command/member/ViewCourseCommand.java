package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.Course;
import com.framja.gymmanagement.model.GymClass;

import java.util.List;

public class ViewCourseCommand implements ActionCommand<List<Course>> {
    private final CourseService courseService;

    public ViewCourseCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public List<Course> execute() {
        return courseService.getAllCourses();
    }
}

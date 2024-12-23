package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public Course getCourseById(int courseId) {
        return courses.stream()
                .filter(course -> course.getId() == courseId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    @Override
    public void createCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void updateCourse(int courseId, Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                courses.set(i, updatedCourse);
                return;
            }
        }
        throw new IllegalArgumentException("Course not found: " + courseId);
    }

    @Override
    public void deleteCourse(int courseId) {
        courses.removeIf(course -> course.getId() == courseId);
    }
}


package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.Course;

import java.util.List;

public interface CourseService {
    Course getCourseById(int courseId);
    List<Course> getAllCourses();
    void createCourse(Course course);
    void updateCourse(int courseId, Course updatedCourse);
    void deleteCourse(int courseId);
}

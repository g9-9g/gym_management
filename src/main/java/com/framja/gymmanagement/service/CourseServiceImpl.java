package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> findAllCourses() {
        return new ArrayList<>(courses);
    }

    @Override
    public Optional<Course> findCourseById(int id) {
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }

    @Override
    public void createCourse(Course course) {
        if (courses.stream().anyMatch(existingCourse -> existingCourse.getId() == course.getId())) {
            throw new IllegalArgumentException("Course with ID " + course.getId() + " already exists.");
        }
        courses.add(course);
    }

    @Override
    public void updateCourse(Course updatedCourse) {
        findCourseById(updatedCourse.getId()).ifPresentOrElse(existingCourse -> {
            // Chỉ cập nhật các trường khác, không thay đổi ID
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setPrice(updatedCourse.getPrice());
            existingCourse.setMaxMembers(updatedCourse.getMaxMembers());
            existingCourse.setStartDate(updatedCourse.getStartDate());
            existingCourse.setEndDate(updatedCourse.getEndDate());
        }, () -> {
            throw new IllegalArgumentException("Course with ID " + updatedCourse.getId() + " not found.");
        });
    }


    @Override
    public boolean deleteCourse(int id) {
        return courses.removeIf(course -> course.getId() == id);
    }
}

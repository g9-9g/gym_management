package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAllCourses(); // Lấy danh sách tất cả các khóa học
    Optional<Course> findCourseById(int id); // Tìm khóa học theo ID
    void createCourse(Course course); // Thêm khóa học mới
    void updateCourse(Course updatedCourse); // Cập nhật thông tin khóa học
    boolean deleteCourse(int id); // Xóa khóa học theo ID
}


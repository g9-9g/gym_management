package com.framja.gymmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class GymClass {
    private final int id;           // ID của lớp học
    private final int courseId;     // ID của khóa học mà lớp học này thuộc về
    private User instructor; // Tên giảng viên
    private String schedule;  // Thời gian học (ví dụ: "Thứ 6, 2h-3h")
    private int maxCapacity;  // Số lượng tối đa học viên
    private final List<User> members; // Danh sách học viên đã đăng ký

    public GymClass(int id, int courseId, User instructor, String schedule, int maxCapacity) {
        this.id = id;
        this.courseId = courseId;
        this.instructor = instructor;
        this.schedule = schedule;
        this.maxCapacity = maxCapacity;
        this.members = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public User getInstructor() {
        return instructor;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isFull() {
        return members.size() >= maxCapacity;
    }

    public void addMember(User user) {
        if (!isFull()) {
            members.add(user);
        } else {
            throw new IllegalStateException("Class is already full.");
        }
    }

    public List<User> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String toString() {
        return "GymClass{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", instructor='" + instructor + '\'' +
                ", schedule='" + schedule + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", members=" + members.size() +
                '}';
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setInstructor(User trainer) {
        this.instructor = trainer;
    }
}

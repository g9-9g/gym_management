package com.framja.gymmanagement.model;

import com.framja.gymmanagement.interfaces.IManageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private double price;
    private int totalSessions;
    private String startDate;
    private String endDate;
    private List<GymClass> classrooms; // List of classes under this course

    public Course(int id, String name, double price, int totalSessions, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalSessions = totalSessions;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classrooms = new ArrayList<>();
    }

    // Add a class to the course
    public void addClassroom(GymClass classroom) {
        classrooms.add(classroom);
        System.out.println("Classroom " + classroom.getName() + " added to course " + name);
    }

    // Get all classes
    public List<GymClass> getClassrooms() {
        return classrooms;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


package com.framja.gymmanagement.model;

import java.util.List;

public class Trainer extends User {
    private String specialization; // Example: Yoga, Swimming
    private Long salary;

    public Trainer(String username, String email, String password) {
        super(username, email, password);
    }

    // Getters and Setters
    public Long getSalary() {
        return salary;
    }
    public void setSalary(Long salary) {
        this.salary = salary;
    }
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
package com.framja.gymmanagement.role;

import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.model.User;

public class Trainer extends User {

    private double salary; // Trường bổ sung dành riêng cho Trainer
    private String specialization;

    public Trainer(String username, String password, String phoneNumber, String gender, String address, double salary) {
        super(username, password, RoleType.TRAINER, phoneNumber, gender, address);
        this.salary = salary;
    }

    public Trainer(String username, String password, double salary) {
        super(username, password, RoleType.TRAINER);
        this.salary = salary;
    }

    // Getter và Setter cho trường salary
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + ", salary=" + salary;
    }
}

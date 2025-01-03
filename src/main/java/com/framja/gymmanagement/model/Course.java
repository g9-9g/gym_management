package com.framja.gymmanagement.model;

public class Course {
    private final int id;           // ID của khóa học
    private String name;      // Tên khóa học
    private String description; // Mô tả khóa học
    private double price;     // Giá tiền của khóa học
    private int maxMembers;   // Số lượng học viên tối đa
    private String startDate; // Ngày bắt đầu khóa học
    private String endDate;   // Ngày kết thúc khóa học

    public Course(int id, String name, String description, double price, int maxMembers, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.maxMembers = maxMembers;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", maxMembers=" + maxMembers +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

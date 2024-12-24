package com.framja.gymmanagement.model;

public class StatisticsDTO {
    private final int totalEquipment;
    private final int totalMembers;
    private final int totalTrainers;
    private final int totalCourses;

    public StatisticsDTO(int totalEquipment, int totalMembers, int totalTrainers, int totalCourses) {
        this.totalEquipment = totalEquipment;
        this.totalMembers = totalMembers;
        this.totalTrainers = totalTrainers;
        this.totalCourses = totalCourses;
    }

    public int getTotalEquipment() {
        return totalEquipment;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public int getTotalTrainers() {
        return totalTrainers;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
                "totalEquipment=" + totalEquipment +
                ", totalMembers=" + totalMembers +
                ", totalTrainers=" + totalTrainers +
                ", totalCourses=" + totalCourses +
                '}';
    }
}

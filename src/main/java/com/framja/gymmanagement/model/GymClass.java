package com.framja.gymmanagement.model;

import com.framja.gymmanagement.interfaces.ISchedulable;

import java.util.ArrayList;
import java.util.List;

public class GymClass implements ISchedulable {
    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String fixedSchedule; // Fixed schedule (day, time)
    private int trainerId; // Trainer ID
    private int maxCapacity; // Maximum number of members allowed
    private List<Long> memberIds; // List of member IDs

    public GymClass(Long id, String name, String startTime, String endTime, String fixedSchedule, int trainerId, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fixedSchedule = fixedSchedule;
        this.trainerId = trainerId;
        this.maxCapacity = maxCapacity;
        this.memberIds = new ArrayList<>();
    }

    // Add a member to the class
    public boolean addMember(Long memberId) {
        if (memberIds.contains(memberId)) {
            System.out.println("Member already enrolled.");
            return false;
        }
        if (memberIds.size() >= maxCapacity) {
            System.out.println("Class is full. Cannot add more members.");
            return false;
        }
        memberIds.add(memberId);
        System.out.println("Member added to class: " + this.name);
        return true;
    }

    // Remove a member from the class
    public boolean removeMember(Long memberId) {
        if (!memberIds.contains(memberId)) {
            System.out.println("Member not found in this class.");
            return false;
        }
        memberIds.remove(memberId);
        System.out.println("Member removed from class: " + this.name);
        return true;
    }

    // Check if a member is in the class
    public boolean isMemberEnrolled(Long memberId) {
        return memberIds.contains(memberId);
    }

    // Get all enrolled members
    public List<Long> getAllMembers() {
        return new ArrayList<>(memberIds); // Return a copy of the list to avoid modification outside
    }

    // Clear all members
    public void clearAllMembers() {
        memberIds.clear();
        System.out.println("All members have been removed from the class.");
    }

    // Other methods for scheduling
    @Override
    public void schedule(String day, String time) {
        this.fixedSchedule = day + " " + time;
        System.out.println("Classroom scheduled: " + fixedSchedule);
    }

    @Override
    public void updateSchedule(String newDay, String newTime) {
        this.fixedSchedule = newDay + " " + newTime;
        System.out.println("Classroom schedule updated: " + fixedSchedule);
    }

    @Override
    public void cancelSchedule() {
        this.fixedSchedule = null;
        System.out.println("Classroom schedule canceled");
    }

    // Getters and Setters
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}


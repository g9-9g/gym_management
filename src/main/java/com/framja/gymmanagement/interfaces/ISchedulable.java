package com.framja.gymmanagement.interfaces;

public interface ISchedulable {
    void schedule(String day, String time);
    void updateSchedule(String newDay, String newTime);
    void cancelSchedule();
}

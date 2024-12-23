package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.GymClass;

import java.util.List;

public interface ClassService {
    GymClass getClassById(int classId);
    List<GymClass> getClassesByCourseId(int courseId);
    List<GymClass> getAllClasses();
    void createGymClass(GymClass gymClass);
    void updateGymClass(int classId, GymClass updatedGymClass);
    void deleteGymClass(int classId);
}

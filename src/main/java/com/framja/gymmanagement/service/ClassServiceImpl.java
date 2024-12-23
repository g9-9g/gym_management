package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.model.GymClass;

import java.util.ArrayList;
import java.util.List;

public class ClassServiceImpl implements ClassService {
    private final List<GymClass> gymClasses = new ArrayList<>();

    @Override
    public GymClass getClassById(int classId) {
        return gymClasses.stream()
                .filter(gymClass -> gymClass.getId() == classId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<GymClass> getClassesByCourseId(int courseId) {
        List<GymClass> classes = new ArrayList<>();
        for (GymClass gymClass : gymClasses) {
            if (gymClass.getCourseId() == courseId) {
                classes.add(gymClass);
            }
        }
        return classes;
    }

    @Override
    public List<GymClass> getAllClasses() {
        return gymClasses;
    }

    @Override
    public void createGymClass(GymClass gymClass) {
        gymClasses.add(gymClass);
    }

    @Override
    public void updateGymClass(int classId, GymClass updatedGymClass) {
        for (int i = 0; i < gymClasses.size(); i++) {
            if (gymClasses.get(i).getId() == classId) {
                gymClasses.set(i, updatedGymClass);
                return;
            }
        }
        throw new IllegalArgumentException("GymClass not found: " + classId);
    }

    @Override
    public void deleteGymClass(int classId) {
        gymClasses.removeIf(gymClass -> gymClass.getId() == classId);
    }
}

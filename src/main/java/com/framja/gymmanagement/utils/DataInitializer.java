package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.role.Admin;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;

import java.time.LocalDate;

public class DataInitializer {

    public static void initializeData(ServiceContainer serviceContainer) {
        // Get service instances
        UserService userService = serviceContainer.getService(UserService.class);
        MembershipService membershipService = serviceContainer.getService(MembershipService.class);
        PaymentService paymentService = serviceContainer.getService(PaymentService.class);
        CourseService courseService = serviceContainer.getService(CourseService.class);
        ClassService classService = serviceContainer.getService(ClassService.class);

        // Create Trainer
        Trainer trainer = new Trainer("trainer_user", "trainerpass", "456789123", "Male", "789 Trainer St", 5000.0);
        userService.addUser(trainer);

        // Create Members
        Member john = new Member("abc", "abc", "123456789", "Male", "123 Main St", null);
        MembershipCard johnCard = new MembershipCard(MembershipCardType.BASIC, LocalDate.now(), LocalDate.now().plusMonths(3), john);
        john.setMembershipCard(johnCard);
        membershipService.addMembershipCard(johnCard);
        paymentService.createPayment(new Payment("P001", john, trainer, 50.0, LocalDate.now(), "Basic Membership Payment"));
        userService.addUser(john);

        Member jane = new Member("jane_smith", "password456", "987654321", "Female", "456 Elm St", null);
        MembershipCard janeCard = new MembershipCard(MembershipCardType.PREMIUM, LocalDate.now(), LocalDate.now().plusMonths(6), jane);
        jane.setMembershipCard(janeCard);
        membershipService.addMembershipCard(janeCard);
        paymentService.createPayment(new Payment("P002", jane, trainer, 100.0, LocalDate.now(), "Premium Membership Payment"));
        userService.addUser(jane);

        // Create Admin
        Admin admin = new Admin("a", "123", RoleType.ADMIN, "1122334455", "Non-binary", "789 Admin St");
        userService.addUser(admin);

        // Create Courses
        Course yogaCourse = new Course(1, "Yoga for Beginners", "A gentle introduction to yoga.", 100.0, 20, "2024-01-10", "2024-04-10");
        Course liftingCourse = new Course(2, "Advanced Weightlifting", "Strength training for advanced athletes.", 200.0, 15, "2024-02-01", "2024-05-01");
        Course cardioCourse = new Course(3, "Cardio Blast", "High-intensity cardio workouts.", 150.0, 25, "2024-03-01", "2024-06-01");
        Course swimmingCourse = new Course(4, "Swimming Basics", "Learn to swim with confidence.", 120.0, 10, "2024-02-15", "2024-05-15");
        courseService.createCourse(yogaCourse);
        courseService.createCourse(liftingCourse);
        courseService.createCourse(cardioCourse);
        courseService.createCourse(swimmingCourse);

        // Create GymClasses
        GymClass yogaClass1 = new GymClass(1, yogaCourse.getId(), trainer, "Monday, 6 PM - 7 PM", 20);
        GymClass yogaClass2 = new GymClass(2, yogaCourse.getId(), trainer, "Wednesday, 7 AM - 8 AM", 20);
        GymClass liftingClass1 = new GymClass(3, liftingCourse.getId(), trainer, "Tuesday, 8 AM - 9 AM", 15);
        GymClass liftingClass2 = new GymClass(4, liftingCourse.getId(), trainer, "Thursday, 6 PM - 7 PM", 15);
        GymClass cardioClass1 = new GymClass(5, cardioCourse.getId(), trainer, "Friday, 7 AM - 8 AM", 25);
        GymClass cardioClass2 = new GymClass(6, cardioCourse.getId(), trainer, "Saturday, 5 PM - 6 PM", 25);
        GymClass swimmingClass1 = new GymClass(7, swimmingCourse.getId(), trainer, "Sunday, 8 AM - 9 AM", 10);
        GymClass swimmingClass2 = new GymClass(8, swimmingCourse.getId(), trainer, "Sunday, 3 PM - 4 PM", 10);
        classService.createGymClass(yogaClass1);
        classService.createGymClass(yogaClass2);
        classService.createGymClass(liftingClass1);
        classService.createGymClass(liftingClass2);
        classService.createGymClass(cardioClass1);
        classService.createGymClass(cardioClass2);
        classService.createGymClass(swimmingClass1);
        classService.createGymClass(swimmingClass2);

        // Enroll Members in GymClasses
        yogaClass1.addMember(john);
        yogaClass2.addMember(jane);
        liftingClass1.addMember(jane);
        cardioClass1.addMember(john);
        cardioClass2.addMember(jane);
        swimmingClass1.addMember(john);
        swimmingClass2.addMember(jane);

        // Update relationships
        john.setMembershipCard(johnCard);
        jane.setMembershipCard(janeCard);
    }
}

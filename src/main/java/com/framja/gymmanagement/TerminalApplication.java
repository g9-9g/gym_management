package com.framja.gymmanagement;

import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.utils.DataInitializer;
import com.framja.gymmanagement.utils.ServiceContainer;
import com.framja.gymmanagement.utils.SessionManager;

import java.util.List;


public class TerminalApplication {
    public static void main(String[] args) {
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        UserService userService = serviceContainer.getService(UserService.class);
        DataInitializer.initializeData(serviceContainer);

        // Simulate login
        User loggedInUser = userService.findUserByUsername("abc").orElse(null);
        if (loggedInUser == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
        System.out.println("Role: " + loggedInUser.getRole());

        if (!(loggedInUser instanceof Member member)) {
            System.out.println("Only Members can perform these actions!");
            return;
        }

        // Assign to session
        SessionManager.getInstance().setCurrentUser(member);

        // Test all commands
        testCommands(member);

        // Clear session
        SessionManager.getInstance().clearSession();
        System.out.println("\nSession cleared!");
    }

    private static void testCommands(Member member) {
        // View Profile
        System.out.println("\nTesting: View Profile");
        ActionResult<User> profileResult = member.performAction(MemberMenuConstants.VIEW_PROFILE);
        System.out.println(profileResult.isSuccess() ? profileResult.getData() : profileResult.getMessage());

        // Update Profile
        System.out.println("\nTesting: Update Profile");
        ActionResult<Boolean> updateProfileResult = member.performAction(
                MemberMenuConstants.UPDATE_PROFILE, "new_username", "new_password");
        System.out.println(updateProfileResult.isSuccess() ? "Profile updated successfully." : updateProfileResult.getMessage());

        profileResult = member.performAction(MemberMenuConstants.VIEW_PROFILE);
        System.out.println(profileResult.isSuccess() ? profileResult.getData() : profileResult.getMessage());


        // View Payment History
        System.out.println("\nTesting: View Payment History");
        ActionResult<List<Payment>> paymentHistoryResult = member.performAction(MemberMenuConstants.VIEW_PAYMENT_HISTORY);
        System.out.println(paymentHistoryResult.isSuccess() ? paymentHistoryResult.getData() : paymentHistoryResult.getMessage());

        // Register Membership
        System.out.println("\nTesting: Register Membership");
        ActionResult<MembershipCard> registerMembershipResult = member.performAction(
                MemberMenuConstants.REGISTER_MEMBERSHIP, MembershipCardType.PREMIUM);
        System.out.println(registerMembershipResult.isSuccess() ? registerMembershipResult.getData() : registerMembershipResult.getMessage());

        // View Membership
        System.out.println("\nTesting: View Membership");
        ActionResult<MembershipCard> viewMembershipResult = member.performAction(MemberMenuConstants.VIEW_MEMBERSHIP);
        System.out.println(viewMembershipResult.isSuccess() ? viewMembershipResult.getData() : viewMembershipResult.getMessage());

        // View All Courses
        System.out.println("\nTesting: View All Courses");
        ActionResult<List<Course>> viewAllCoursesResult = member.performAction(MemberMenuConstants.VIEW_ALL_COURSES);
        System.out.println(viewAllCoursesResult.isSuccess() ? viewAllCoursesResult.getData() : viewAllCoursesResult.getMessage());

        // View Gym Classes From a Specific Course
        System.out.println("\nTesting: View Gym Classes From Course");
        int courseId = 1; // Assuming course ID 1 exists in the test data
        ActionResult<List<GymClass>> viewGymClassesResult = member.performAction(MemberMenuConstants.VIEW_CLASSES_FROM_COURSE, courseId);
        System.out.println(viewGymClassesResult.isSuccess() ? viewGymClassesResult.getData() : viewGymClassesResult.getMessage());

        // Register for a Gym Class
        System.out.println("\nTesting: Register for Gym Class");
        int gymClassId = 1; // Assuming gym class ID 1 exists in the test data
        ActionResult<Boolean> registerGymClassResult = member.performAction(MemberMenuConstants.REGISTER_FOR_CLASS, gymClassId);
        System.out.println(registerGymClassResult.isSuccess() ? "Successfully registered for Gym Class ID: " + gymClassId
                : registerGymClassResult.getMessage());

        // View Participated Gym Classes
        System.out.println("\nTesting: View Participated Gym Classes");
        ActionResult<List<GymClass>> viewParticipatedClassesResult = member.performAction(MemberMenuConstants.VIEW_PARTICIPATED_CLASSES);
        System.out.println(viewParticipatedClassesResult.isSuccess() ? viewParticipatedClassesResult.getData()
                : viewParticipatedClassesResult.getMessage());

    }
}


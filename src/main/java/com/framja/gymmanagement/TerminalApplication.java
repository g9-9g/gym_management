package com.framja.gymmanagement;

import com.framja.gymmanagement.constants.AdminMenuConstants;
import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.role.Admin;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;
import com.framja.gymmanagement.utils.DataInitializer;
import com.framja.gymmanagement.utils.ServiceContainer;
import com.framja.gymmanagement.utils.SessionManager;

import java.time.LocalDate;
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

        User adminUser = userService.findUserByUsername("admin_user").orElse(null);
        if (adminUser == null) {
            System.out.println("Admin not found!");
            return;
        }

        if (!(adminUser instanceof Admin admin)) {
            System.out.println("Only Members can perform these actions!");
            return;
        }

        testAdminCommands(admin);

        // Clear session
        SessionManager.getInstance().clearSession();
        System.out.println("\nSession cleared!");
    }

    private static void testCommands(Member member) {
        // View Profile
        System.out.println("\nTesting: View Profile");
        ActionResult<User> profileResult = member.performAction(MemberMenuConstants.VIEW_PROFILE);
        System.out.println(profileResult.isSuccess() ? profileResult.getData() : profileResult.getMessage());

        System.out.println("\nTesting: Update Profile");

        // Tạo đối tượng Member mới với các thông tin cập nhật
        Member updatedMember = new Member(
                member.getUsername(),          // Dùng lại username để xác định user cần cập nhật
                "updated_password",            // Mật khẩu mới
                member.getPhoneNumber(),       // Dùng lại số điện thoại hiện tại
                member.getGender(),            // Dùng lại giới tính hiện tại
                "456 Updated Address",         // Địa chỉ mới
                member.getMembershipCard()     // Giữ nguyên MembershipCard
        );

        // Thực thi lệnh cập nhật thông tin
        ActionResult<Boolean> updateProfileResult = member.performAction(
                MemberMenuConstants.UPDATE_PROFILE, updatedMember
        );

        // Hiển thị kết quả
        System.out.println(updateProfileResult.isSuccess()
                ? "Profile updated successfully."
                : updateProfileResult.getMessage());

        // Kiểm tra lại thông tin sau khi cập nhật
        ActionResult<User> updatedProfileResult = member.performAction(MemberMenuConstants.VIEW_PROFILE);
        System.out.println(updatedProfileResult.isSuccess()
                ? updatedProfileResult.getData()
                : updatedProfileResult.getMessage());





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

        // Test View Trainer List
        System.out.println("\nTesting: View Trainer List");
        ActionResult<List<Trainer>> trainerListResult = member.performAction(10);
        System.out.println(trainerListResult.isSuccess() ? trainerListResult.getData() : trainerListResult.getMessage());

        // TEST ADMIN:


    }

    private static void testAdminCommands(Admin admin) {
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        CourseService courseService = serviceContainer.getService(CourseService.class);
        ClassService classService = serviceContainer.getService(ClassService.class);

        System.out.println("\nTesting: Add Equipment");
        Equipment newEquipment = new Equipment(
                9999, "Treadmill", "High-quality treadmill", "Cardio", "FitnessPro", 1200.0, 5,
                "Room 101", "Available", LocalDate.now().minusMonths(3), LocalDate.now().plusYears(2)
        );
        ActionResult<Boolean> addEquipmentResult = admin.performAction(1, newEquipment);
        System.out.println(addEquipmentResult.isSuccess() ? "Equipment added successfully!" : addEquipmentResult.getMessage());

        // Verify Add Equipment
        ActionResult<List<Equipment>> verifyAddEquipment = admin.performAction(4);
        System.out.println("Equipment list after adding:");
        System.out.println(verifyAddEquipment.isSuccess() ? verifyAddEquipment.getData() : verifyAddEquipment.getMessage());

        System.out.println("\nTesting: Update Equipment");
        newEquipment.setDescription("Updated treadmill description");
        ActionResult<Boolean> updateEquipmentResult = admin.performAction(2, newEquipment);
        System.out.println(updateEquipmentResult.isSuccess() ? "Equipment updated successfully!" : updateEquipmentResult.getMessage());

        // Verify Update Equipment
        verifyAddEquipment = admin.performAction(4);
        System.out.println("Equipment list after updating:");
        System.out.println(verifyAddEquipment.isSuccess() ? verifyAddEquipment.getData() : verifyAddEquipment.getMessage());

        System.out.println("\nTesting: Remove Equipment");
        ActionResult<Boolean> removeEquipmentResult = admin.performAction(3, newEquipment.getId());
        System.out.println(removeEquipmentResult.isSuccess() ? "Equipment removed successfully!" : removeEquipmentResult.getMessage());

        // Verify Remove Equipment
        verifyAddEquipment = admin.performAction(AdminMenuConstants.VIEW_ALL_EQUIPMENT);
        System.out.println("Equipment list after removing:");
        System.out.println(verifyAddEquipment.isSuccess() ? verifyAddEquipment.getData() : verifyAddEquipment.getMessage());

        System.out.println("\nTesting: Add User");
        Member newMember = new Member("new_member", "password123", "999999999", "Female", "123 New St", null);
        ActionResult<Boolean> addUserResult = admin.performAction(5, newMember);
        System.out.println(addUserResult.isSuccess() ? "User added successfully!" : addUserResult.getMessage());

        // Verify Add User
        ActionResult<List<User>> verifyAddUser = admin.performAction(AdminMenuConstants.FIND_ALL_USERS);
        System.out.println("User list after adding:");
        System.out.println(verifyAddUser.isSuccess() ? verifyAddUser.getData() : verifyAddUser.getMessage());

        System.out.println("\nTesting: Update Member");
        newMember.setAddress("456 Updated St");
        ActionResult<Boolean> updateMemberResult = admin.performAction(6, newMember);
        System.out.println(updateMemberResult.isSuccess() ? "Member updated successfully!" : updateMemberResult.getMessage());

        // Verify Update Member
        ActionResult<User> verifyUpdatedMember = admin.performAction(10, "new_member");
        System.out.println("Updated member:");
        System.out.println(verifyUpdatedMember.isSuccess() ? verifyUpdatedMember.getData() : verifyUpdatedMember.getMessage());

        System.out.println("\nTesting: Update Trainer");
        Trainer newTrainer = new Trainer("trainer_user", "newpass456", "888888888", "Male", "789 Trainer St", 5500.0, "BOXING");
        newTrainer.setSpecialization("Cardio Specialist");
        ActionResult<Boolean> updateTrainerResult = admin.performAction(7, newTrainer);
        System.out.println(updateTrainerResult.isSuccess() ? "Trainer updated successfully!" : updateTrainerResult.getMessage());

        // Verify Update Trainer
        ActionResult<User> verifyUpdatedTrainer = admin.performAction(10, "trainer_user");
        System.out.println("Updated trainer:");
        System.out.println(verifyUpdatedTrainer.isSuccess() ? verifyUpdatedTrainer.getData() : verifyUpdatedTrainer.getMessage());

        System.out.println("\nTesting: Remove User");
        ActionResult<Boolean> removeUserResult = admin.performAction(8, "new_member");
        System.out.println(removeUserResult.isSuccess() ? "User removed successfully!" : removeUserResult.getMessage());

        // Verify Remove User
        verifyAddUser = admin.performAction(9);
        System.out.println("User list after removing:");
        System.out.println(verifyAddUser.isSuccess() ? verifyAddUser.getData() : verifyAddUser.getMessage());

        System.out.println("\nTesting: Find All Users");
        ActionResult<List<User>> findAllUsersResult = admin.performAction(9);
        System.out.println("All users:");
        System.out.println(findAllUsersResult.isSuccess() ? findAllUsersResult.getData() : findAllUsersResult.getMessage());

        System.out.println("\nTesting: Find User By Username");
        ActionResult<User> findUserByUsernameResult = admin.performAction(10, "trainer_user");
        System.out.println("Found user:");
        System.out.println(findUserByUsernameResult.isSuccess() ? findUserByUsernameResult.getData() : findUserByUsernameResult.getMessage());


        System.out.println("\nTesting: Create Course");
        Course newCourse = new Course(999, "Test Course", "Description for test course", 500.0, 10, "2024-01-01", "2024-06-01");
        int initialCourseCount = courseService.findAllCourses().size();
        ActionResult<Boolean> createCourseResult = admin.performAction(12, newCourse);
        int updatedCourseCount = courseService.findAllCourses().size();
        System.out.println(createCourseResult.isSuccess() ? "Course created successfully!" : createCourseResult.getMessage());
        System.out.println("Courses before: " + initialCourseCount + ", after: " + updatedCourseCount);

        System.out.println("\nTesting: Read All Courses");
        ActionResult<List<Course>> readAllCoursesResult = admin.performAction(13);
        System.out.println(readAllCoursesResult.isSuccess() ? readAllCoursesResult.getData() : readAllCoursesResult.getMessage());

        System.out.println("\nTesting: Update Course");
        newCourse = new Course(newCourse.getId(), "Updated Course Name", "Updated course description", 600.0, 15, "2024-02-01", "2024-07-01");
        ActionResult<Boolean> updateCourseResult = admin.performAction(14, newCourse);
        System.out.println(updateCourseResult.isSuccess() ? "Course updated successfully!" : updateCourseResult.getMessage());

        // Verify Update
        ActionResult<List<Course>> verifyUpdateCourses = admin.performAction(13);
        System.out.println("Courses after update:");
        System.out.println(verifyUpdateCourses.isSuccess() ? verifyUpdateCourses.getData() : verifyUpdateCourses.getMessage());

        System.out.println("\nTesting: Delete Course");
        ActionResult<Boolean> deleteCourseResult = admin.performAction(15, newCourse.getId());
        System.out.println(deleteCourseResult.isSuccess() ? "Course deleted successfully!" : deleteCourseResult.getMessage());

        // Verify Deletion
        int finalCourseCount = courseService.findAllCourses().size();
        System.out.println("Courses before deletion: " + updatedCourseCount + ", after: " + finalCourseCount);

        System.out.println("\nTesting: Create GymClass");
        GymClass newGymClass = new GymClass(999, 1, admin, "Monday, 6 AM - 7 AM", 10);
        int initialGymClassCount = classService.getAllClasses().size();
        ActionResult<Boolean> createGymClassResult = admin.performAction(16, newGymClass);
        int updatedGymClassCount = classService.getAllClasses().size();
        System.out.println(createGymClassResult.isSuccess() ? "GymClass created successfully!" : createGymClassResult.getMessage());
        System.out.println("GymClasses before: " + initialGymClassCount + ", after: " + updatedGymClassCount);

        System.out.println("\nTesting: View All GymClasses");
        ActionResult<List<GymClass>> viewAllGymClassesResult = admin.performAction(17);
        System.out.println(viewAllGymClassesResult.isSuccess() ? viewAllGymClassesResult.getData() : viewAllGymClassesResult.getMessage());

        System.out.println("\nTesting: Update GymClass");
        newGymClass.setSchedule("Tuesday, 7 AM - 8 AM");
        ActionResult<Boolean> updateGymClassResult = admin.performAction(18, newGymClass.getId(), newGymClass);
        System.out.println(updateGymClassResult.isSuccess() ? "GymClass updated successfully!" : updateGymClassResult.getMessage());

        // Verify Update
        ActionResult<List<GymClass>> verifyUpdatedGymClasses = admin.performAction(17);
        System.out.println("GymClasses after update:");
        System.out.println(verifyUpdatedGymClasses.isSuccess() ? verifyUpdatedGymClasses.getData() : verifyUpdatedGymClasses.getMessage());

        System.out.println("\nTesting: Delete GymClass");
        ActionResult<Boolean> deleteGymClassResult = admin.performAction(19, newGymClass.getId());
        System.out.println(deleteGymClassResult.isSuccess() ? "GymClass deleted successfully!" : deleteGymClassResult.getMessage());

        // Verify Deletion
        int finalGymClassCount = classService.getAllClasses().size();
        System.out.println("GymClasses before deletion: " + updatedGymClassCount + ", after: " + finalGymClassCount);
    }


}


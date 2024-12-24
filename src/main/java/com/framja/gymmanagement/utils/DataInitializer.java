package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.role.Admin;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;

import java.time.LocalDate;
import java.util.Random;

public class DataInitializer {

    public static void initializeData(ServiceContainer serviceContainer) {
        // Get service instances
        UserService userService = serviceContainer.getService(UserService.class);
        MembershipService membershipService = serviceContainer.getService(MembershipService.class);
        PaymentService paymentService = serviceContainer.getService(PaymentService.class);
        CourseService courseService = serviceContainer.getService(CourseService.class);
        ClassService classService = serviceContainer.getService(ClassService.class);
        EquipmentService equipmentService = serviceContainer.getService(EquipmentService.class);

        initializeUsers(userService);
        initializeMembershipCards(membershipService, userService, paymentService);
        initializeCourses(courseService);
        initializeGymClasses(classService, courseService, userService);
        initializeEquipment(equipmentService);
    }

    private static void initializeUsers(UserService userService) {
        // Tạo Admin
        Admin admin1 = new Admin("admin1", "admin123", RoleType.ADMIN, "999888777", "Non-binary", "123 Admin Blvd");
        Admin admin2 = new Admin("admin2", "admin234", RoleType.ADMIN, "888777666", "Female", "456 Admin Road");
        userService.addUser(admin1);
        userService.addUser(admin2);

        // Tạo Trainer
        for (int i = 1; i <= 20; i++) {
            Trainer trainer = new Trainer(
                    "trainer" + i,
                    "password",
                    "10000" + i,
                    i % 2 == 0 ? "Male" : "Female",
                    "Trainer Address " + i,
                    4000.0 + (i * 100),
                    i % 3 == 0 ? "YOGA" : (i % 3 == 1 ? "CARDIO" : "STRENGTH")
            );
            userService.addUser(trainer);
        }

        // Tạo Member
        for (int i = 1; i <= 100; i++) {
            Member member = new Member(
                    "member" + i,
                    "password",
                    "20000" + i,
                    i % 3 == 0 ? "Male" : (i % 3 == 1 ? "Female" : "Non-binary"),
                    "Member Address " + i,
                    null // Membership card sẽ được thêm sau
            );
            userService.addUser(member);
        }
    }

    private static void initializeMembershipCards(MembershipService membershipService, UserService userService, PaymentService paymentService) {
        int cardId = 1; // Đếm ID của MembershipCard
        for (int i = 1; i <= 100; i++) { // Có 100 Member từ hàm initializeUsers
            Member member = (Member) userService.findUserByUsername("member" + i).orElse(null);
            if (member != null) {
                MembershipCardType cardType = i % 3 == 0 ? MembershipCardType.BASIC : (i % 3 == 1 ? MembershipCardType.PREMIUM : MembershipCardType.VIP);
                LocalDate startDate = LocalDate.now().minusDays(i % 30); // Ngày bắt đầu cách nhau 1-30 ngày
                LocalDate endDate = startDate.plusMonths(cardType == MembershipCardType.BASIC ? 3 : (cardType == MembershipCardType.PREMIUM ? 6 : 12));

                MembershipCard card = new MembershipCard(
                        cardType,
                        startDate,
                        endDate,
                        member
                );
                membershipService.addMembershipCard(card);
                member.setMembershipCard(card); // Liên kết thẻ với Member

                // Tạo một khoản thanh toán liên quan đến thẻ
                Payment payment = new Payment(
                        "P" + cardId,
                        member,
                        null, // Có thể gán Trainer nếu cần
                        cardType == MembershipCardType.BASIC ? 50.0 : (cardType == MembershipCardType.PREMIUM ? 100.0 : 150.0),
                        startDate,
                        cardType + " Membership Payment"
                );
                paymentService.createPayment(payment);
                cardId++;
            }
        }
    }


    private static void initializeCourses(CourseService courseService) {
        for (int i = 1; i <= 10; i++) { // Tạo 10 khóa học
            String courseName = "Course " + i + " - " + (i % 2 == 0 ? "Cardio" : "Strength");
            String description = "Description for " + courseName + ": A great course for improving your fitness.";
            double price = 100.0 + (i * 20); // Giá tăng dần
            int maxParticipants = 10 + (i * 5); // Số lượng người tham gia tối đa tăng dần
            String startDate = LocalDate.now().plusDays(i * 2).toString();
            String endDate = LocalDate.now().plusDays(i * 2 + 60).toString();

            Course course = new Course(
                    i,
                    courseName,
                    description,
                    price,
                    maxParticipants,
                    startDate,
                    endDate
            );

            courseService.createCourse(course);
        }
    }

    private static void initializeGymClasses(ClassService classService, CourseService courseService, UserService userService) {
        Random random = new Random();
        int classId = 1;

        for (int courseId = 1; courseId <= 10; courseId++) { // Duyệt qua từng khóa học
            Course course = courseService.findCourseById(courseId).orElse(null);
            if (course != null) {
                int numberOfClasses = random.nextInt(5) + 1; // Số lớp ngẫu nhiên (từ 1 đến 5)

                for (int session = 1; session <= numberOfClasses; session++) {
                    Trainer trainer = (Trainer) userService.findUserByUsername("trainer" + ((classId % 20) + 1)).orElse(null); // Gán Trainer theo vòng lặp
                    String schedule = "Session " + session + ": " + (session % 2 == 0 ? "Evening" : "Morning");
                    int capacity = course.getMaxMembers() / 2; // Dung lượng mỗi lớp bằng một nửa dung lượng khóa học

                    GymClass gymClass = new GymClass(
                            classId,
                            course.getId(),
                            trainer,
                            schedule,
                            capacity
                    );

                    classService.createGymClass(gymClass);

                    // Gán ngẫu nhiên số lượng Member (1 đến dung lượng lớp) vào lớp
                    int numberOfMembers = random.nextInt(capacity) + 1; // Số thành viên ngẫu nhiên
                    for (int memberCount = 1; memberCount <= numberOfMembers; memberCount++) {
                        Member member = (Member) userService.findUserByUsername("member" + random.nextInt(100) + 1).orElse(null);
                        if (member != null) {
                            gymClass.addMember(member); // Thêm thành viên vào lớp
                        }
                    }

                    classId++;
                }
            }
        }
    }

    private static void initializeEquipment(EquipmentService equipmentService) {
        Random random = new Random();
        String[] equipmentNames = {
                "Treadmill", "Bench Press", "Dumbbell Set", "Yoga Mat", "Elliptical Trainer",
                "Rowing Machine", "Kettlebell Set", "Pull-up Bar", "Leg Press Machine",
                "Cycling Bike", "Resistance Bands", "Medicine Ball", "Foam Roller"
        };
        String[] categories = {"Cardio", "Strength", "Flexibility", "Swimming"};
        String[] brands = {"TechFit", "IronForge", "PowerGear", "ZenFlow", "MoveIt", "RowStrong", "GymTools", "SwimMaster"};
        String[] locations = {"Main Gym Hall", "Strength Training Room", "Yoga Studio", "Cardio Area", "Swimming Pool"};
        String[] conditions = {"Excellent", "Good", "Fair", "Worn"};

        for (int i = 1; i <= 50; i++) { // Tạo 50 thiết bị
            String name = equipmentNames[random.nextInt(equipmentNames.length)] + " " + (i + 100);
            String category = categories[random.nextInt(categories.length)];
            String brand = brands[random.nextInt(brands.length)];
            String location = locations[random.nextInt(locations.length)];
            String condition = conditions[random.nextInt(conditions.length)];

            double price = 100.0 + random.nextInt(1000); // Giá từ 100 đến 1100
            int quantity = random.nextInt(10) + 1; // Số lượng từ 1 đến 10
            LocalDate purchaseDate = LocalDate.now().minusMonths(random.nextInt(36)); // Mua cách đây 0-36 tháng
            LocalDate warrantyEndDate = purchaseDate.plusYears(1 + random.nextInt(3)); // Bảo hành 1-3 năm sau ngày mua

            Equipment equipment = new Equipment(
                    i, // ID thiết bị
                    name,
                    "High-quality " + category.toLowerCase() + " equipment for gym users.",
                    category,
                    brand,
                    price,
                    quantity,
                    location,
                    condition,
                    purchaseDate,
                    warrantyEndDate
            );

            equipmentService.addEquipment(equipment);
        }
    }
}


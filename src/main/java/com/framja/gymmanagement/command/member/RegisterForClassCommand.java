package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.model.Course;
import com.framja.gymmanagement.model.GymClass;
import com.framja.gymmanagement.model.Payment;
import com.framja.gymmanagement.model.User;

import java.time.LocalDate;
import java.util.UUID;

public class RegisterForClassCommand implements ActionCommand<Boolean> {
    private final User user;
    private final MembershipService membershipService;
    private final ClassService classService;
    private final CourseService courseService;
    private final PaymentService paymentService;
    private final int classId;

    public RegisterForClassCommand(User user,
                                   MembershipService membershipService,
                                   ClassService classService,
                                   CourseService courseService,
                                   PaymentService paymentService,
                                   int classId) {
        this.user = user;
        this.membershipService = membershipService;
        this.classService = classService;
        this.courseService = courseService;
        this.paymentService = paymentService;
        this.classId = classId;
    }

    @Override
    public Boolean execute() {
        // 1. Kiểm tra membership
//        if (!user) {
//            throw new IllegalStateException("Membership is not active. Please renew your membership.");
//        }

        // 2. Lấy lớp học
        GymClass gymClass = classService.getClassById(classId);
        if (gymClass == null) {
            throw new IllegalArgumentException("Class not found.");
        }

        // 3. Kiểm tra nếu lớp đã đầy
        if (gymClass.isFull()) {
            throw new IllegalStateException("Class is already full.");
        }

        // 4. Lấy thông tin khóa học
        int courseId = gymClass.getCourseId();
        Course course = courseService.getCourseById(courseId);
        if (course == null) throw new IllegalArgumentException("Course not exists");
        double coursePrice = course.getPrice();
        // 5. Tạo thanh toán
        User instructor = gymClass.getInstructor();
        if (instructor == null) {
            throw new IllegalStateException("ADU");
        }
        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                user,
                instructor,
                coursePrice,
                LocalDate.now(),
                "Payment for class " + gymClass.getId() + " (" + gymClass.getSchedule() + ")"
        );

        Payment createdPayment = paymentService.createPayment(payment);

        if (createdPayment == null) {
            throw new RuntimeException("Payment creation failed. Please try again.");
        }

        // 6. Thêm người dùng vào lớp học
        gymClass.addMember(user);

        // 7. Cập nhật lớp học
        classService.updateGymClass(classId, gymClass);

        return true;
    }
}

package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.member.*;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class MemberCommandFactory {
    private final User user;
    private final MembershipService membershipService;
    private final ClassService classService;
    private final CourseService courseService;
    private final PaymentService paymentService;

    public MemberCommandFactory(User user) {
        this.user = user;
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        this.membershipService = serviceContainer.getService(MembershipService.class);
        this.paymentService = serviceContainer.getService(PaymentService.class);
        this.classService = serviceContainer.getService(ClassService.class);
        this.courseService = serviceContainer.getService(CourseService.class);
    }

    public ViewProfileCommand createViewProfileCommand() {
        return new ViewProfileCommand(user);
    }

    public UpdateProfileCommand createUpdateProfileCommand(String newUsername, String newPassword) {
        return new UpdateProfileCommand(user, newUsername, newPassword);
    }

    public ViewParticipatedGymClass createViewParticipatedGymClassCommand() {
        return new ViewParticipatedGymClass(user, classService);
    }

    public ViewCourseCommand createViewCourseCommand() {
        return new ViewCourseCommand(courseService);
    }

    public ViewGymClassFromCourseCommand createViewGymClassFromCourseCommand(int courseId) {
        return new ViewGymClassFromCourseCommand(classService, courseId);
    }

    public ViewMembershipCommand createViewMembershipCommand() {
        return new ViewMembershipCommand(user, membershipService);
    }

    public RegisterForClassCommand createRegisterForClassCommand(int classId) {
        return new RegisterForClassCommand(user, membershipService, classService, courseService, paymentService, classId);
    }

    public ViewPaymentHistoryCommand createViewPaymentHistoryCommand() {
        return new ViewPaymentHistoryCommand(paymentService, user);
    }

    public ActionCommand<MembershipCard> createRegisterMembershipCommand(MembershipCardType cardType) {
        return new RegisterMembershipCommand(user, membershipService, cardType);
    }

}

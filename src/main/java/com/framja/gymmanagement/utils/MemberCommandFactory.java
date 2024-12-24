package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.member.*;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.interfaces.CommandFactory;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class MemberCommandFactory implements CommandFactory {
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

    @Override
    public List<MenuOption<?>> createMenuOptions() {
        return List.of(
                new MenuOption<>(1, "View Profile", CommandFactory.create(args -> new ViewProfileCommand(user))),
                new MenuOption<>(2, "Update Profile", CommandFactory.create(args -> {
                    if (args.length < 2 || !(args[0] instanceof String) || !(args[1] instanceof String)) {
                        throw new IllegalArgumentException("Invalid arguments for Update Profile");
                    }
                    return new UpdateProfileCommand(user, (String) args[0], (String) args[1]);
                })),
                new MenuOption<>(3, "View Participated Gym Classes", CommandFactory.create(args -> new ViewParticipatedGymClass(user, classService))),
                new MenuOption<>(4, "View All Courses", CommandFactory.create(args -> new ViewCourseCommand(courseService))),
                new MenuOption<>(5, "View Classes From Course", CommandFactory.create(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for View Classes From Course");
                    }
                    return new ViewGymClassFromCourseCommand(classService, (Integer) args[0]);
                })),
                new MenuOption<>(6, "View Membership", CommandFactory.create(args -> new ViewMembershipCommand(user, membershipService))),
                new MenuOption<>(7, "Register for Class", CommandFactory.create(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Register for Class");
                    }
                    return new RegisterForClassCommand(user, membershipService, classService, courseService, paymentService, (Integer) args[0]);
                })),
                new MenuOption<>(8, "View Payment History", CommandFactory.create(args -> new ViewPaymentHistoryCommand(paymentService, user))),
                new MenuOption<>(9, "Register Membership", CommandFactory.create(args -> {
                    if (args.length < 1 || !(args[0] instanceof MembershipCardType)) {
                        throw new IllegalArgumentException("Invalid arguments for Register Membership");
                    }
                    return new RegisterMembershipCommand(user, membershipService, (MembershipCardType) args[0]);
                }))
        );
    }
}

package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.admin.UpdateMemberCommand;
import com.framja.gymmanagement.command.member.*;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.interfaces.CommandFactory;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;

import java.util.List;

public class MemberCommandFactory implements CommandFactory {
    private final User user;
    private final MembershipService membershipService;
    private final ClassService classService;
    private final CourseService courseService;
    private final PaymentService paymentService;
    private final UserService userService;

    public MemberCommandFactory(User user) {
        this.user = user;
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        this.membershipService = serviceContainer.getService(MembershipService.class);
        this.paymentService = serviceContainer.getService(PaymentService.class);
        this.classService = serviceContainer.getService(ClassService.class);
        this.courseService = serviceContainer.getService(CourseService.class);
        this.userService = serviceContainer.getService(UserService.class);
    }

    @Override
    public List<MenuOption<?>> createMenuOptions() {
        return List.of(
                new MenuOption<>(1, "View Profile", CommandFactory.createSupplier(args -> new ViewProfileCommand(user))),
                new MenuOption<>(2, "Update Profile", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Member)) {
                        throw new IllegalArgumentException("Invalid arguments for Update Member");
                    }
                    return new UpdateMemberCommand(userService, (Member) args[0]);
                })),
                new MenuOption<>(3, "View Participated Gym Classes", CommandFactory.createSupplier(args -> new ViewParticipatedGymClass(user, classService))),
                new MenuOption<>(4, "View All Courses", CommandFactory.createSupplier(args -> new ViewCourseCommand(courseService))),
                new MenuOption<>(5, "View Classes From Course", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for View Classes From Course");
                    }
                    return new ViewGymClassFromCourseCommand(classService, (Integer) args[0]);
                })),
                new MenuOption<>(6, "View Membership", CommandFactory.createSupplier(args -> new ViewMembershipCommand(user, membershipService))),
                new MenuOption<>(7, "Register for Class", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Register for Class");
                    }
                    return new RegisterForClassCommand(user, membershipService, classService, courseService, paymentService, (Integer) args[0]);
                })),
                new MenuOption<>(8, "View Payment History", CommandFactory.createSupplier(args -> new ViewPaymentHistoryCommand(paymentService, user))),
                new MenuOption<>(9, "Register Membership", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof MembershipCardType)) {
                        throw new IllegalArgumentException("Invalid arguments for Register Membership");
                    }
                    return new RegisterMembershipCommand(user, membershipService, (MembershipCardType) args[0]);
                })),
                new MenuOption<>(10, "View Trainer List", CommandFactory.createSupplier(args -> new ViewTrainerListCommand(userService)))
        );
    }
}

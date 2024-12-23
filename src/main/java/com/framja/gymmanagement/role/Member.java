package com.framja.gymmanagement.role;

import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.model.BaseRole;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.utils.CommandFactory;
import com.framja.gymmanagement.utils.MemberCommandFactory;

import java.util.List;

public class Member extends BaseRole {

    public Member(User self) {
        super(self);
    }

    @Override
    protected List<MenuOption<?>> defineMenuOptions() {
        MemberCommandFactory factory = new MemberCommandFactory(self);
        return List.of(
                new MenuOption<>(MemberMenuConstants.VIEW_PROFILE, "View Profile",
                        CommandFactory.create(args -> factory.createViewProfileCommand())),
                new MenuOption<>(MemberMenuConstants.UPDATE_PROFILE, "Update Profile",
                        CommandFactory.create(args -> {
                            if (args.length < 2 || !(args[0] instanceof String) || !(args[1] instanceof String)) {
                                throw new IllegalArgumentException("Invalid arguments for Update Profile");
                            }
                            return factory.createUpdateProfileCommand((String) args[0], (String) args[1]);
                        })),
                new MenuOption<>(MemberMenuConstants.VIEW_PARTICIPATED_CLASSES, "View Participated Classes",
                        CommandFactory.create(args -> factory.createViewParticipatedGymClassCommand())),
                new MenuOption<>(MemberMenuConstants.VIEW_ALL_COURSES, "View All Courses",
                        CommandFactory.create(args -> factory.createViewCourseCommand())),
                new MenuOption<>(MemberMenuConstants.VIEW_CLASSES_FROM_COURSE, "View Classes From Course",
                        CommandFactory.create(args -> {
                            if (args.length < 1 || !(args[0] instanceof Integer)) {
                                throw new IllegalArgumentException("Invalid arguments for View Classes From Course");
                            }
                            return factory.createViewGymClassFromCourseCommand((Integer) args[0]);
                        })),
                new MenuOption<>(MemberMenuConstants.VIEW_MEMBERSHIP, "View Membership",
                        CommandFactory.create(args -> factory.createViewMembershipCommand())),
                new MenuOption<>(MemberMenuConstants.REGISTER_FOR_CLASS, "Register For Class",
                        CommandFactory.create(args -> {
                            if (args.length < 1 || !(args[0] instanceof Integer)) {
                                throw new IllegalArgumentException("Invalid arguments for Register For Class");
                            }
                            return factory.createRegisterForClassCommand((Integer) args[0]);
                        })),
                new MenuOption<>(MemberMenuConstants.VIEW_PAYMENT_HISTORY, "View Payment History",
                        CommandFactory.create(args -> factory.createViewPaymentHistoryCommand()
                        )),
                new MenuOption<>(MemberMenuConstants.REGISTER_MEMBERSHIP, "Register Membership",
                        CommandFactory.create(args -> {
                            if (args.length < 1 || !(args[0] instanceof MembershipCardType)) {
                                throw new IllegalArgumentException("Invalid arguments for Register Membership.");
                            }
                            return factory.createRegisterMembershipCommand((MembershipCardType) args[0]);
                        }))
        );
    }
}

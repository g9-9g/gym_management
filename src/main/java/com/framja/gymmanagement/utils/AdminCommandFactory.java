package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.admin.*;
import com.framja.gymmanagement.command.member.ViewCourseCommand;
import com.framja.gymmanagement.command.member.ViewGymClassFromCourseCommand;
import com.framja.gymmanagement.command.member.ViewParticipatedGymClass;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;

import java.util.List;

public class AdminCommandFactory implements CommandFactory {
    private final User user;

    public AdminCommandFactory(User user) {
        this.user = user;
    }


    @Override
    public List<MenuOption<?>> createMenuOptions() {
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        EquipmentService equipmentService = serviceContainer.getService(EquipmentService.class);
        UserService userService = serviceContainer.getService(UserService.class);
        CourseService courseService = serviceContainer.getService(CourseService.class);
        ClassService classService = serviceContainer.getService(ClassService.class);

        return List.of(
                new MenuOption<>(1, "Add Equipment", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Equipment)) {
                        throw new IllegalArgumentException("Invalid arguments for Add Equipment");
                    }
                    return new AddEquipmentCommand(equipmentService, (Equipment) args[0]);
                })),
                new MenuOption<>(2, "Update Equipment", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Equipment)) {
                        throw new IllegalArgumentException("Invalid arguments for Update Equipment");
                    }
                    return new UpdateEquipmentCommand(equipmentService, (Equipment) args[0]);
                })),
                new MenuOption<>(3, "Remove Equipment", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Remove Equipment");
                    }
                    return new RemoveEquipmentCommand(equipmentService, (Integer) args[0]);
                })),
                new MenuOption<>(4, "View All Equipment", CommandFactory.createSupplier(args ->
                        new ViewAllEquipmentCommand(equipmentService)
                )),

                // User management
                new MenuOption<>(5, "Add User", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof User)) {
                        throw new IllegalArgumentException("Invalid arguments for Add User");
                    }
                    return new AddUserCommand(userService, (User) args[0]);
                })),
                new MenuOption<>(6, "Update Member", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Member member)) {
                        throw new IllegalArgumentException("Invalid arguments for Update Member");
                    }
                    return new UpdateMemberCommand(userService, member);
                })),
                new MenuOption<>(7, "Update Trainer", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Trainer)) {
                        throw new IllegalArgumentException("Invalid arguments for Update Trainer");
                    }
                    return new UpdateTrainerCommand(userService, (Trainer) args[0]);
                })),
                new MenuOption<>(8, "Remove User", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof String)) {
                        throw new IllegalArgumentException("Invalid arguments for Remove User");
                    }
                    return new RemoveUserCommand(userService, (String) args[0]);
                })),
                new MenuOption<>(9, "Find All Users", CommandFactory.createSupplier(args ->
                        new FindAllUsersCommand(userService)
                )),
                new MenuOption<>(10, "Find User By Username", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof String)) {
                        throw new IllegalArgumentException("Invalid arguments for Find User By Username");
                    }
                    return new FindUserByUsernameCommand(userService, (String) args[0]);
                })),
                new MenuOption<>(11, "Get Statistics", CommandFactory.createSupplier(args ->
                        new GetStatisticsCommand(equipmentService, userService, courseService)
                )),

                // Course Management
                new MenuOption<>(12, "Create Course", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Course)) {
                        throw new IllegalArgumentException("Invalid arguments for Create Course");
                    }
                    return new CreateCourseCommand(courseService, (Course) args[0]);
                })),
                new MenuOption<>(13, "Read All Courses", CommandFactory.createSupplier(args ->
                        new ReadAllCoursesCommand(courseService)
                )),
                new MenuOption<>(14, "Update Course", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Course)) {
                        throw new IllegalArgumentException("Invalid arguments for Update Course");
                    }
                    return new UpdateCourseCommand(courseService, (Course) args[0]);
                })),
                new MenuOption<>(15, "Delete Course", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Delete Course");
                    }
                    return new DeleteCourseCommand(courseService, (Integer) args[0]);
                })),
                new MenuOption<>(16, "Create GymClass", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof GymClass)) {
                        throw new IllegalArgumentException("Invalid arguments for Create GymClass");
                    }
                    return new CreateGymClassCommand(classService, (GymClass) args[0]);
                })),
                new MenuOption<>(17, "View All GymClasses", CommandFactory.createSupplier(args ->
                        new ReadGymClassCommand(classService))),
                new MenuOption<>(18, "Update GymClass", CommandFactory.createSupplier(args -> {
                    if (args.length < 2 || !(args[0] instanceof Integer) || !(args[1] instanceof GymClass)) {
                        throw new IllegalArgumentException("Invalid arguments for Update GymClass");
                    }
                    return new UpdateGymClassCommand(classService, (Integer) args[0], (GymClass) args[1]);
                })),
                new MenuOption<>(19, "Delete GymClass", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Delete GymClass");
                    }
                    return new DeleteGymClassCommand(classService, (Integer) args[0]);
                })),
                new MenuOption<>(20, "View Classes From Course", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for View Classes From Course");
                    }
                    return new ViewGymClassFromCourseCommand(classService, (Integer) args[0]);
                })),
                new MenuOption<>(21, "View All Courses", CommandFactory.createSupplier(args -> new ViewCourseCommand(courseService)))

        );
    }

}

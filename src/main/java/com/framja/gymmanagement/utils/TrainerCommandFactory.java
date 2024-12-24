package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.member.ViewCourseCommand;
import com.framja.gymmanagement.command.member.ViewGymClassFromCourseCommand;
import com.framja.gymmanagement.command.member.ViewParticipatedGymClass;
import com.framja.gymmanagement.command.member.ViewTrainerListCommand;
import com.framja.gymmanagement.command.trainer.AssignTrainerCommand;
import com.framja.gymmanagement.command.trainer.ViewReceivedPaymentsCommand;
import com.framja.gymmanagement.command.trainer.ViewTrainerClassCommand;
import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class TrainerCommandFactory implements CommandFactory {
    private final User self;
    private final ClassService classService;
    private final PaymentService paymentService;
    private final CourseService courseService;
    private final UserService userService;

    public TrainerCommandFactory(User self) {
        this.self = self;
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        this.classService = serviceContainer.getService(ClassService.class);
        this.paymentService = serviceContainer.getService(PaymentService.class);
        this.courseService = serviceContainer.getService(CourseService.class);
        this.userService = serviceContainer.getService(UserService.class);

    }

    @Override
    public List<MenuOption<?>> createMenuOptions() {
        return List.of(
                new MenuOption<>(1, "View My Classes", CommandFactory.createSupplier(args -> new ViewTrainerClassCommand(self, classService))),
                new MenuOption<>(2, "View Received Payments", CommandFactory.createSupplier(args -> new ViewReceivedPaymentsCommand(self, paymentService))),
                new MenuOption<>(3, "Assign Trainer to Class", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Assign Trainer to Class.");
                    }
                    return new AssignTrainerCommand(self, (Integer) args[0], classService);
                })),
                new MenuOption<>(4, "View Trainer List", CommandFactory.createSupplier(args -> new ViewTrainerListCommand(userService))),
                new MenuOption<>(5, "View Classes From Course", CommandFactory.createSupplier(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for View Classes From Course");
                    }
                    return new ViewGymClassFromCourseCommand(classService, (Integer) args[0]);
                })),
                new MenuOption<>(6, "View All Courses", CommandFactory.createSupplier(args -> new ViewCourseCommand(courseService)))
        );
    }
}

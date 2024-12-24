package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.trainer.AssignTrainerCommand;
import com.framja.gymmanagement.command.trainer.ViewReceivedPaymentsCommand;
import com.framja.gymmanagement.command.trainer.ViewTrainerClassCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.interfaces.CommandFactory;
import com.framja.gymmanagement.interfaces.PaymentService;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class TrainerCommandFactory implements CommandFactory {
    private final User self;
    private final ClassService classService;
    private final PaymentService paymentService;

    public TrainerCommandFactory(User self) {
        this.self = self;
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        this.classService = serviceContainer.getService(ClassService.class);
        this.paymentService = serviceContainer.getService(PaymentService.class);
    }

    @Override
    public List<MenuOption<?>> createMenuOptions() {
        return List.of(
                new MenuOption<>(1, "View My Classes", CommandFactory.create(args -> new ViewTrainerClassCommand(self, classService))),
                new MenuOption<>(2, "View Received Payments", CommandFactory.create(args -> new ViewReceivedPaymentsCommand(self, paymentService))),
                new MenuOption<>(3, "Assign Trainer to Class", CommandFactory.create(args -> {
                    if (args.length < 1 || !(args[0] instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid arguments for Assign Trainer to Class.");
                    }
                    return new AssignTrainerCommand(self, (Integer) args[0], classService);
                }))
        );
    }
}

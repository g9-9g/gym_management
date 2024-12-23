package com.framja.gymmanagement.role;

import com.framja.gymmanagement.constants.TrainerMenuConstants;
import com.framja.gymmanagement.model.BaseRole;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.utils.CommandFactory;
import com.framja.gymmanagement.utils.TrainerCommandFactory;

import java.util.List;

public class Trainer extends BaseRole {
    public Trainer(User self) {
        super(self);
    }

    @Override
    protected List<MenuOption<?>> defineMenuOptions() {
        TrainerCommandFactory factory = new TrainerCommandFactory(self);

        return List.of(
                new MenuOption<>(TrainerMenuConstants.VIEW_TRAINER_CLASSES, "View My Classes",
                        CommandFactory.create(args -> factory.createViewTrainerClassCommand())),
                new MenuOption<>(TrainerMenuConstants.VIEW_RECEIVED_PAYMENTS, "View Received Payments",
                        CommandFactory.create(args -> factory.createViewReceivedPaymentsCommand())),
                new MenuOption<>(TrainerMenuConstants.ASSIGN_TRAINER_TO_CLASS, "Assign Trainer to Class",
                        CommandFactory.create(args -> {
                            if (args.length < 1 || !(args[0] instanceof Integer)) {
                                throw new IllegalArgumentException("Invalid arguments for Assign Trainer to Class.");
                            }
                            return factory.createAssignTrainerCommand((Integer) args[0]);
                        }))
        );
    }
}

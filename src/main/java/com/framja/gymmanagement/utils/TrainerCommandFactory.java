package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.command.trainer.AssignTrainerCommand;
import com.framja.gymmanagement.command.trainer.ViewReceivedPaymentsCommand;
import com.framja.gymmanagement.command.trainer.ViewTrainerClassCommand;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.interfaces.PaymentService;
import com.framja.gymmanagement.model.User;

public class TrainerCommandFactory {
    private final User self;
    private final ClassService classService;
    private final PaymentService paymentService;

    public TrainerCommandFactory(User self) {
        this.self = self;
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        this.classService = serviceContainer.getService(ClassService.class);
        this.paymentService = serviceContainer.getService(PaymentService.class);
    }

    public ViewTrainerClassCommand createViewTrainerClassCommand() {
        return new ViewTrainerClassCommand(self, classService);
    }

    public ViewReceivedPaymentsCommand createViewReceivedPaymentsCommand() {
        return new ViewReceivedPaymentsCommand(self, paymentService);
    }

    public AssignTrainerCommand createAssignTrainerCommand(int gymClassId) {
        return new AssignTrainerCommand(self, gymClassId, classService);
    }
}

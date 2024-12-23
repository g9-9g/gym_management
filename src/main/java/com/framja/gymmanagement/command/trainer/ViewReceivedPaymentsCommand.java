package com.framja.gymmanagement.command.trainer;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.PaymentService;
import com.framja.gymmanagement.model.Payment;
import com.framja.gymmanagement.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ViewReceivedPaymentsCommand implements ActionCommand<List<Payment>> {
    private final User trainer;
    private final PaymentService paymentService;

    public ViewReceivedPaymentsCommand(User trainer, PaymentService paymentService) {
//        if (!"Trainer".equals(trainer.getRole())) {
//            throw new IllegalArgumentException("User must be a Trainer.");
//        }
        this.trainer = trainer;
        this.paymentService = paymentService;
    }

    @Override
    public List<Payment> execute() {
        return paymentService.getAllPayments().stream()
                .filter(payment -> payment.getReceiver() != null && payment.getReceiver().equals(trainer))
                .collect(Collectors.toList());
    }
}

package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.PaymentService;
import com.framja.gymmanagement.model.Payment;
import com.framja.gymmanagement.model.User;

import java.util.List;

public class ViewPaymentHistoryCommand implements ActionCommand<List<Payment>> {
    private final PaymentService paymentService;
    private final User user;

    public ViewPaymentHistoryCommand(PaymentService paymentService, User user) {
        this.paymentService = paymentService;
        this.user = user;
    }

    @Override
    public List<Payment> execute() {
        return paymentService.getPaymentsByUser(user.getUsername());
    }
}

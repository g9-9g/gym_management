package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.PaymentService;
import com.framja.gymmanagement.model.Payment;
import com.framja.gymmanagement.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {
    private final List<Payment> payments = new ArrayList<>();

    public PaymentServiceImpl() {
        // dữ liệu mock
//        payments.add(new Payment("P001", new User("john_doe", "password123", "Member"), 50.0, LocalDate.of(2023, 1, 1), "Monthly Gym Membership"));
//        payments.add(new Payment("P002", new User("john_doe", "password123", "Member"), 100.0, LocalDate.of(2023, 3, 1), "Quarterly Gym Membership"));
//        payments.add(new Payment("P003", new User("jane_doe", "password456", "Member"), 70.0, LocalDate.of(2023, 2, 1), "Yoga Class Payment"));
    }

    @Override
    public Payment createPayment(Payment payment) {
        payments.add(payment);
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    @Override
    public List<Payment> getPaymentsByUser(String userId) {
        return payments.stream()
                .filter(payment -> payment.getUser().getUsername().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Payment updatePayment(String paymentId, Payment updatedPayment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId().equals(paymentId)) {
                payments.set(i, updatedPayment);
                return updatedPayment;
            }
        }
        throw new IllegalArgumentException("Payment not found: " + paymentId);
    }

    @Override
    public boolean deletePayment(String paymentId) {
        return payments.removeIf(payment -> payment.getPaymentId().equals(paymentId));
    }
}

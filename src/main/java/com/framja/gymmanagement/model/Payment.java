package com.framja.gymmanagement.model;

import java.time.LocalDate;

public class Payment {
    private final String paymentId; // ID thanh toán
    private final User user;        // Người dùng thực hiện thanh toán
    private final User receiver;
    private final double amount;    // Số tiền thanh toán
    private final LocalDate date;   // Ngày thanh toán
    private final String description; // Mô tả thêm

    public Payment(String paymentId, User user, User receiver, double amount, LocalDate date, String description) {
        this.paymentId = paymentId;
        this.user = user;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", user=" + user +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    public String getPaymentId() {
        return paymentId;
    }

    public User getUser() {
        return user;
    }

    public User getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

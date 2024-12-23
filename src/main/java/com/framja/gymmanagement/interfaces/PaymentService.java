package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.Payment;

import java.util.List;

public interface PaymentService {
    /**
     * Tạo thanh toán mới.
     * @param payment Đối tượng Payment cần tạo.
     * @return Payment đã tạo.
     */
    Payment createPayment(Payment payment);

    /**
     * Lấy danh sách lịch sử thanh toán của tất cả người dùng.
     * @return Danh sách Payment.
     */
    List<Payment> getAllPayments();

    /**
     * Lấy danh sách lịch sử thanh toán của một người dùng cụ thể.
     * @param userId ID của người dùng.
     * @return Danh sách Payment của người dùng.
     */
    List<Payment> getPaymentsByUser(String userId);

    /**
     * Cập nhật thông tin thanh toán.
     * @param paymentId ID của thanh toán cần cập nhật.
     * @param updatedPayment Đối tượng Payment mới.
     * @return Payment đã cập nhật.
     */
    Payment updatePayment(String paymentId, Payment updatedPayment);

    /**
     * Xóa thanh toán.
     * @param paymentId ID của thanh toán cần xóa.
     * @return true nếu xóa thành công, false nếu thất bại.
     */
    boolean deletePayment(String paymentId);
}

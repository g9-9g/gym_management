package com.framja.gymmanagement.constants;

/**
 * Hằng số đại diện cho các tùy chọn menu của vai trò Member.
 * Các hằng số này được sử dụng với phương thức `performAction` để thực thi các lệnh cụ thể cho Member.
 *
 * Ví dụ sử dụng:
 * - Gọi `performAction(MemberMenuConstants.VIEW_PROFILE)` để xem thông tin cá nhân của người dùng.
 * - Gọi `performAction(MemberMenuConstants.UPDATE_PROFILE, "newName", "newPhone")` để cập nhật thông tin cá nhân.
 *
 * Tham số:
 * - Mỗi hằng số chỉ định tham số yêu cầu (nếu có) và kiểu dữ liệu trả về của hành động tương ứng.
 */
public class MemberMenuConstants {

    /** Xem thông tin cá nhân của người dùng. Không yêu cầu tham số. Trả về đối tượng User. */
    public static final int VIEW_PROFILE = 1;

    /** Cập nhật thông tin cá nhân của người dùng.
     * Tham số: [String name, String phone].
     * Trả về Boolean (true nếu thành công).
     */
    public static final int UPDATE_PROFILE = 2;

    /** Xem danh sách tất cả lớp học mà người dùng đang tham gia.
     * Không yêu cầu tham số.
     * Trả về List<GymClass>.
     */
    public static final int VIEW_PARTICIPATED_CLASSES = 3;

    /** Xem danh sách tất cả khóa học.
     * Không yêu cầu tham số.
     * Trả về List<Course>.
     */
    public static final int VIEW_ALL_COURSES = 4;

    /** Xem danh sách các lớp học thuộc một khóa học cụ thể.
     * Tham số: [Integer courseId].
     * Trả về List<GymClass>.
     */
    public static final int VIEW_CLASSES_FROM_COURSE = 5;

    /** Xem thông tin chi tiết về Membership của người dùng.
     * Không yêu cầu tham số.
     * Trả về MembershipCard.
     */
    public static final int VIEW_MEMBERSHIP = 6;

    /** Đăng ký tham gia một lớp học.
     * Tham số: [Integer gymClassId].
     * Trả về Boolean (true nếu đăng ký thành công).
     */
    public static final int REGISTER_FOR_CLASS = 7;

    /** Xem lịch sử thanh toán của người dùng.
     * Không yêu cầu tham số.
     * Trả về List<Payment>.
     */
    public static final int VIEW_PAYMENT_HISTORY = 8;

    /** Đăng ký một Membership mới.
     * Tham số: [MembershipCardType cardType].
     * Trả về MembershipCard.
     */
    public static final int REGISTER_MEMBERSHIP = 9;

    // Constructor riêng tư để ngăn việc khởi tạo lớp.
    private MemberMenuConstants() {
    }
}

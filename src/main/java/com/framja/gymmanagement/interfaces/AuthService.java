package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.User;

public interface AuthService {
    /**
     * Đăng nhập vào hệ thống.
     *
     * @param username Tên đăng nhập của người dùng.
     * @param password Mật khẩu của người dùng.
     * @return User nếu đăng nhập thành công, null nếu không thành công.
     */
    User login(String username, String password);

    /**
     * Đăng ký tài khoản mới.
     *
     * @param username Tên đăng nhập mới.
     * @param password Mật khẩu mới.
     * @param role Vai trò của người dùng (e.g., MEMBER, TRAINER, ADMIN).
     * @return User đã được tạo, null nếu thất bại (username đã tồn tại).
     */
    User register(String username, String password, String role);
}

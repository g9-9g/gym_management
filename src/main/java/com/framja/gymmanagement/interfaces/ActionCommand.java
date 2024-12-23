package com.framja.gymmanagement.interfaces;

public interface ActionCommand<T> {
    /**
     * Thực thi hành động với các tham số tùy chọn.
     * @return Kết quả của hành động (kiểu trả về generic).
     */
    T execute();
}

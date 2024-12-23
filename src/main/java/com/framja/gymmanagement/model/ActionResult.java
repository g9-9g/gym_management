package com.framja.gymmanagement.model;

public class ActionResult<T> {
    private final boolean success;  // Trạng thái hành động: thành công hay thất bại
    private final String message;   // Thông điệp liên quan đến hành động
    private final T data;           // Dữ liệu bổ sung liên quan đến hành động

    public ActionResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ActionResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

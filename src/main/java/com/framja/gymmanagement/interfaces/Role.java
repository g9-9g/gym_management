package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.ActionResult;
import com.framja.gymmanagement.model.MenuOption;

import java.util.List;

public interface Role {
    /**
     * Trả về danh sách các tùy chọn menu của vai trò.
     * @return Danh sách các tùy chọn menu.
     */
    List<MenuOption<?>> getMenuOptions();

    /**
     * Thực hiện một hành động dựa trên ID của hành động và các tham số bổ sung.
     * @param actionId ID của hành động cần thực hiện.
     * @param args Các tham số bổ sung cần cho hành động.
     * @return Kết quả thực thi hành động dưới dạng ActionResult.
     */
     <T> ActionResult<T> performAction(int actionId, Object... args);
}

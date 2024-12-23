package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.interfaces.ActionCommand;

import java.util.function.Function;

public class CommandFactory {
    /**
     * Tạo Command với tham số khởi tạo.
     * @param commandSupplier Supplier tạo Command.
     * @param <T> Kiểu dữ liệu trả về của Command.
     * @return Command đã khởi tạo.
     */
    public static <T> Function<Object[], ActionCommand<T>> create(Function<Object[], ActionCommand<T>> commandSupplier) {
        return commandSupplier;
    }
}

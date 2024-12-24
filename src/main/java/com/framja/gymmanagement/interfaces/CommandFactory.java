package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.MenuOption;

import java.util.List;
import java.util.function.Function;

public interface CommandFactory {
    List<MenuOption<?>> createMenuOptions();

    static <T> Function<Object[], ActionCommand<T>> createSupplier(Function<Object[], ActionCommand<T>> commandSupplier) {
        return commandSupplier;
    }

}

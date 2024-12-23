package com.framja.gymmanagement.model;

import com.framja.gymmanagement.interfaces.ActionCommand;

import java.util.function.Function;

public class MenuOption<T> {
    private final int id;
    private final String description;
    private final Function<Object[], ActionCommand<T>> commandFactory;

    public MenuOption(int id, String description, Function<Object[], ActionCommand<T>> commandFactory) {
        this.id = id;
        this.description = description;
        this.commandFactory = commandFactory;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ActionCommand<T> createCommand(Object... args) {
        return commandFactory.apply(args);
    }
}

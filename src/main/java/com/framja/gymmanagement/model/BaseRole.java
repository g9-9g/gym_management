package com.framja.gymmanagement.model;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.Role;
import java.util.List;


public abstract class BaseRole implements Role {
    protected final User self;

    public BaseRole(User self) {
        this.self = self;
    }
    protected abstract List<MenuOption<?>> defineMenuOptions();
    private final List<MenuOption<?>> menuOptions;

    {
        this.menuOptions = defineMenuOptions();
    }

    @Override
    public List<MenuOption<?>> getMenuOptions() {
        return menuOptions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ActionResult<T> performAction(int actionId, Object... args) {
        return menuOptions.stream()
                .filter(option -> option.getId() == actionId)
                .findFirst()
                .map(option -> {
                    try {
                        // Tạo command từ menu option
                        ActionCommand<?> rawCommand = option.createCommand(args);
                        // Ép kiểu từ ActionCommand<?> sang ActionCommand<T>
                        ActionCommand<T> command = (ActionCommand<T>) rawCommand;
                        // Thực thi command
                        T result = command.execute();
                        return (ActionResult<T>) new ActionResult<>(true, "Action executed successfully", result);
                    } catch (Exception e) {
                        return (ActionResult<T>) new ActionResult<>(false, "Error executing action: " + e.getMessage(), null);
                    }
                })
                .orElseGet(() -> new ActionResult<>(false, "Invalid action ID", null));
    }
}

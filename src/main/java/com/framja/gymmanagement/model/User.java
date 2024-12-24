package com.framja.gymmanagement.model;

import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.CommandFactory;
import com.framja.gymmanagement.utils.AdminCommandFactory;
import com.framja.gymmanagement.utils.MemberCommandFactory;
import com.framja.gymmanagement.utils.TrainerCommandFactory;

import java.util.List;
import java.util.Objects;

public class User {
    private final String username;
    private String password;
    private RoleType role;
    private String phoneNumber;
    private String gender;
    private String address;

    private List<MenuOption<?>> menuOptions;

    public User(String username, String password, RoleType role, String phoneNumber, String gender, String address) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.menuOptions = defineMenuOptions();
    }

    public User(String username, String password, RoleType role) {
        this(username, password, role, null, null, null);
    }


    public List<MenuOption<?>> getMenuOptions() {
        return menuOptions;
    }

    @SuppressWarnings("unchecked")
    public <T> ActionResult<T> performAction(int actionId, Object... args) {
        return menuOptions.stream()
                .filter(option -> option.getId() == actionId)
                .findFirst()
                .map(option -> {
                    try {
                        // Create command and cast to ActionCommand<T>
                        ActionCommand<T> command = (ActionCommand<T>) option.createCommand(args);

                        // Execute the command
                        T result = command.execute();

                        // Return a successful ActionResult
                        return new ActionResult<>(true, "Action executed successfully", result);
                    } catch (Exception e) {
                        // Return a failure ActionResult
                        return (ActionResult<T>) new ActionResult<>(false, "Error executing action: " + e.getMessage(), null);
                    }
                })
                .orElse(new ActionResult<>(false, "Invalid action ID", null));
    }


    private List<MenuOption<?>> defineMenuOptions() {
        CommandFactory commandFactory = createCommandFactory();
        return commandFactory.createMenuOptions();
    }

    private CommandFactory createCommandFactory() {
        switch (role) {
            case MEMBER:
                return new MemberCommandFactory(this);
            case TRAINER:
                return new TrainerCommandFactory(this);
            case ADMIN:
                return new AdminCommandFactory(this);
            default:
                throw new IllegalStateException("Unsupported role type: " + role);
        }
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", menuOptions=" + menuOptions +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(gender, user.gender) && Objects.equals(address, user.address) && Objects.equals(menuOptions, user.menuOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, phoneNumber, gender, address, menuOptions);
    }
}

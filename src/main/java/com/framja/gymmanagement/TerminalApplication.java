package com.framja.gymmanagement;

import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.ActionResult;
import com.framja.gymmanagement.model.MenuOption;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.utils.ServiceContainer;
import com.framja.gymmanagement.utils.SessionManager;

public class TerminalApplication {
    public static void main(String[] args) {
        UserService userService = ServiceContainer.getInstance().getService(UserService.class);

        // Simulate login
        User loggedInUser = userService.findUserByUsername("john_doe").orElse(null);
        if (loggedInUser == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
        System.out.println("Role: " + loggedInUser.getRole());

        // Check if the user is a Member
        if (!"Member".equals(loggedInUser.getRole())) {
            System.out.println("This application is for Members only!");
            return;
        }

        // Assign the role to SessionManager
        Member member = new Member(loggedInUser);
        SessionManager.getInstance().setCurrentRole(member);

        // Display menu options
        System.out.println("Menu:");
        for (MenuOption<?> option : member.getMenuOptions()) {
            System.out.println(option.getId() + ": " + option.getDescription());
        }

        // Simulate a constant action selection
        System.out.println("\nSimulating action: View All Courses");
        int actionId = MemberMenuConstants.VIEW_ALL_COURSES;

        // Execute the selected option using SessionManager
        ActionResult<?> result = SessionManager.getInstance().getCurrentRole().performAction(actionId);

        // Display the result
        if (result.isSuccess()) {
            System.out.println("Result: " + result.getData());
        } else {
            System.out.println("Error: " + result.getMessage());
        }

        // Simulate another action
        System.out.println("\nSimulating action: View Membership");
        actionId = MemberMenuConstants.VIEW_MEMBERSHIP;

        result = SessionManager.getInstance().getCurrentRole().performAction(actionId);

        if (result.isSuccess()) {
            System.out.println("Result: " + result.getData());
        } else {
            System.out.println("Error: " + result.getMessage());
        }

        // Clear session after testing
        SessionManager.getInstance().clearSession();
        System.out.println("\nUser session cleared!");
    }
}

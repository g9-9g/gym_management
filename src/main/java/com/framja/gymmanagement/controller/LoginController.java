package com.framja.gymmanagement.controller;

import com.framja.gymmanagement.GymApplication;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.model.Role;

import com.framja.gymmanagement.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.util.Objects;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = AuthService.login(username, password);
        if (user != null) {
            redirectToDashboard(user.getRole());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setContentText("Invalid username or password!");
            alert.showAndWait();
        }
    }

    private void redirectToDashboard(Role role) {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root;
            switch (role) {
                case ADMIN:
                    root = FXMLLoader.load(Objects.requireNonNull(GymApplication.class.getResource("hello-view.fxml")));
                    break;
                case MEMBER:
                    root = FXMLLoader.load(Objects.requireNonNull(GymApplication.class.getResource("hello-view.fxml")));
                    break;
                case TRAINER:
                    root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                    break;
                case GYMMANAGER:
                    root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + role);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

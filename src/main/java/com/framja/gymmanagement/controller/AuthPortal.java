package com.framja.gymmanagement.controller;

import java.net.URL;

import java.util.*;

import com.framja.gymmanagement.GymApplication;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.interfaces.AuthService;
import com.framja.gymmanagement.role.Admin;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;
import com.framja.gymmanagement.utils.ServiceContainer;
import com.framja.gymmanagement.utils.SessionManager;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AuthPortal implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_email;

    @FXML
    private TextField register_username;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private Button register_signupBtn;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private ImageView register_loginImage;

    private RoleType cur_page_role = RoleType.ADMIN;

    private AlertPrompt alert = new AlertPrompt();

    private final AuthService authService = ServiceContainer.getInstance().getService(AuthService.class);

    private void redirectToDashboard(User current_user) {
        try {
            Parent root;
            switch (current_user.getRole()) {
                case RoleType.ADMIN:
                    Admin admin = new Admin(current_user);
                    SessionManager.getInstance().setCurrentRole(admin);
                    root = FXMLLoader.load(Objects.requireNonNull(GymApplication.class.getResource("AdminDashboard.fxml")));
                    break;
                case RoleType.MEMBER:
                    Member member = new Member(current_user);
                    SessionManager.getInstance().setCurrentRole(member);
                    root = FXMLLoader.load(Objects.requireNonNull(GymApplication.class.getResource("MemberDashboard.fxml")));
                    break;
                case RoleType.TRAINER:
                    Trainer trainer = new Trainer(current_user);
                    SessionManager.getInstance().setCurrentRole(trainer);
                    root = FXMLLoader.load(Objects.requireNonNull(GymApplication.class.getResource("TrainerDashboard.fxml")));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + current_user.getRole());
            }
            Stage stage = new Stage();
            stage.setTitle("Gym Management System | " + cur_page_role.toString() + "Portal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginAccount() {
        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            try {
                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }
                System.out.println(login_password.getText());
                User cur = authService.login(login_username.getText(), login_password.getText());
                System.out.println(cur.getRole());

                if (cur == null || cur.getRole() != cur_page_role) {
                    alert.errorMessage("Incorrect Username/Password");

                } else {
                    alert.successMessage("Login Successfully!");

                     redirectToDashboard(cur);
                    login_loginBtn.getScene().getWindow().hide();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void registerAccount() {
        if (register_email.getText().isEmpty() || register_username.getText().isEmpty() || register_password.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            try {
                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                if (register_password.getText().length() < 8) {
                    alert.errorMessage("Invalid Password, at least 8 characters needed");
                } else {
                    authService.register(register_username.getText(), register_password.getText(), cur_page_role);

                    alert.successMessage("Registered Successfully!");
                    registerClear();

                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {
        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }

    }

    public void roleList() {
        List<String> roleList = Arrays.asList("Admin Portal", "Trainer Portal", "Gym Manager Portal", "Member Portal");

        ObservableList listData = FXCollections.observableList(roleList);
        login_user.setItems(listData);
    }

    public void switchPage() {
        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {
            cur_page_role = RoleType.ADMIN;
            try {

                Parent root = FXMLLoader.load(GymApplication.class.getResource("admin-portal.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Gym Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (login_user.getSelectionModel().getSelectedItem() == "Trainer Portal") {
            cur_page_role = RoleType.TRAINER;
            try {

                Parent root = FXMLLoader.load(GymApplication.class.getResource("trainer-portal.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Gym Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Member Portal") {
            cur_page_role = RoleType.MEMBER;
            try {

                Parent root = FXMLLoader.load(GymApplication.class.getResource("member-portal.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Gym Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        login_user.getScene().getWindow().hide();

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initializing with URL: " + url.toString());

        if (url != null) {
            String urlPath = url.getPath();
            if (urlPath.contains("admin-portal")) {
                cur_page_role = RoleType.ADMIN;
            } else if (urlPath.contains("member-portal")) {
                cur_page_role = RoleType.MEMBER;
            } else if (urlPath.contains("trainer-portal")) {
                cur_page_role = RoleType.TRAINER;
            } else {
                cur_page_role = RoleType.ADMIN;
            }
        }

        System.out.println("Current Page Role: " + cur_page_role);
        roleList();
    }

}
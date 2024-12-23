package com.framja.gymmanagement.controller;


import java.net.URL;

import java.util.Arrays;
import java.util.ResourceBundle;

import com.framja.gymmanagement.GymApplication;
import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.model.*;

import com.framja.gymmanagement.utils.SessionManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;


import javafx.fxml.FXMLLoader;



import java.util.List;

import javafx.geometry.Insets;

import javafx.scene.layout.StackPane;




public class MemberDashboardController implements Initializable {

    // Top section
    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Button logout_btn;

    // Left navigation
    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button trainers_btn;

    @FXML
    private Button gymclasses_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    // Home form
    @FXML
    private TableView<GymClass> gymClassTableView;

    @FXML
    private TableColumn<GymClass, String> gymClassColDescription;

    @FXML
    private TableColumn<GymClass, String> gymClassColCourse;

    @FXML
    private TableColumn<GymClass, Long> gymClassColTrainer;

    @FXML
    private TableColumn<GymClass, String> gymClassColSchedule;

    @FXML
    private TableColumn<GymClass, String> gymClassColTime;

    @FXML
    private Circle home_doctor_circle;

    @FXML
    private Label cur_card_name;

    @FXML
    private Label cur_card_begin_date;

    @FXML
    private Label cur_card_end_date;


    @FXML
    private TableView<GymClass> ptTableView;

    @FXML
    private TableColumn<GymClass, String> ptColDescription;

    @FXML
    private TableColumn<GymClass, String> ptColTrainer;

    @FXML
    private TableColumn<GymClass, String> ptColDay;

    @FXML
    private TableColumn<GymClass, String> ptColTime;

    @FXML
    private TableColumn<?, ?> home_appointment_col_doctor;

    @FXML
    private TableColumn<?, ?> home_appointment_col_schedule;

    // Doctors form
    @FXML
    private ScrollPane trainers_scrollPane;

    @FXML
    private GridPane trainers_gridPane;

    @FXML
    private ScrollPane courses_scrollPane;

    @FXML
    private GridPane courses_gridPane;

    // Appointments form
    @FXML
    private Label appointment_ad_name;

    @FXML
    private Label appointment_ad_mobileNumber;

    @FXML
    private Label appointment_ad_gender;

    @FXML
    private Label appointment_ad_address;

    @FXML
    private Label appointment_ad_description;

    @FXML
    private Label appointment_ad_doctorName;

    @FXML
    private Label appointment_ad_specialization;

    @FXML
    private Label appointment_ad_schedule;

    @FXML
    private Button appointmentBookBtn;

    @FXML
    private TextArea appointment_d_description;

    @FXML
    private ComboBox<String> appointment_d_doctor;

    @FXML
    private DatePicker appointment_d_schedule;

    @FXML
    private Button appointment_d_confirmBtn;

    @FXML
    private Button appointment_d_clearBtn;

    // Profile form
    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_patientID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_mobileNumber;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_patientID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private TextField profile_password;

    @FXML
    private TextArea profile_address;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private AnchorPane home_form, trainers_form, appointments_form, profile_form, gymclasses_form;

    // Button action methods
    @FXML
    private void logoutBtn() {
        System.out.println("Logout button clicked");
    }



    private <T> void centerAlignColumnContent(TableColumn<GymClass, T> column) {
        column.setCellFactory(tc -> {
            TableCell<GymClass, T> cell = new TableCell<>() {
                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.toString());
                        setGraphic(null);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
    }

    private void loadUserInfo() {
        int actionId = MemberMenuConstants.VIEW_PROFILE;
        ActionResult<User> result = SessionManager.getInstance().getCurrentRole().performAction(actionId);
        if (result.isSuccess()) {
            System.out.println(result.getData()); // Error
//            nav_username.setText(result.getData().getUsername());
//            top_username.setText(result.getData().getUsername());
        } else {
            System.out.println("Error loading User Information");
        }

    }

    private void loadGymClass() {

        int actionId = MemberMenuConstants.VIEW_PARTICIPATED_CLASSES;
        ActionResult<List<GymClass>> result = SessionManager.getInstance().getCurrentRole().performAction(actionId);

        System.out.println(result);

        if (result.isSuccess()) {
            System.out.println("Result: " + result.getData());
            gymClassColDescription.setCellValueFactory(new PropertyValueFactory<>("id"));
            gymClassColCourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
            gymClassColTrainer.setCellValueFactory(new PropertyValueFactory<>("instructor"));
            gymClassColSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));

            centerAlignColumnContent(gymClassColDescription);
            centerAlignColumnContent(gymClassColCourse);
            centerAlignColumnContent(gymClassColTrainer);
            centerAlignColumnContent(gymClassColSchedule);
            centerAlignColumnContent(gymClassColTime);

            gymClassTableView.setItems(FXCollections.observableArrayList(result.getData()));
        } else {
            System.out.println("Error: " + result.getMessage());
        }


    }

    private void loadPT() {
        int actionId = MemberMenuConstants.VIEW_PARTICIPATED_CLASSES;
        ActionResult<List<GymClass>> result = SessionManager.getInstance().getCurrentRole().performAction(actionId);

        if (result.isSuccess()) {
            ptColDescription.setCellValueFactory(new PropertyValueFactory<>("id")); // Assuming ID is appointment ID
            ptColDay.setCellValueFactory(new PropertyValueFactory<>("schedule"));
            ptColTrainer.setCellValueFactory(new PropertyValueFactory<>("instructor"));
            ptColTime.setCellValueFactory(new PropertyValueFactory<>("schedule"));

            centerAlignColumnContent(ptColDescription);
            centerAlignColumnContent(ptColDay);
            centerAlignColumnContent(ptColTrainer);
            centerAlignColumnContent(ptColTime);

            // Set items in the table
            ptTableView.setItems(FXCollections.observableArrayList(result.getData()));
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }

    public void loadTrainerCard() {

        // DATA



//        trainers_gridPane.getChildren().clear();
//        trainers_gridPane.getColumnConstraints().clear();
//        trainers_gridPane.getRowConstraints().clear();
//
//        int row = 0, column = 0;
//
//        for (int q = 0; q < trainers.size(); q++) {
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(GymApplication.class.getResource("TrainerCard.fxml"));
//                StackPane stack = loader.load();
//
//                TrainerCardController dController = loader.getController();
//                dController.setData(trainers.get(q));
//
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//
//                trainers_gridPane.add(stack, column++, row);
//
//                GridPane.setMargin(stack, new Insets(15));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }

    public void loadCourseCard () {
        int actionId = MemberMenuConstants.VIEW_ALL_COURSES;
        ActionResult<List<Course>> result = SessionManager.getInstance().getCurrentRole().performAction(actionId);


        courses_gridPane.getChildren().clear();
        courses_gridPane.getColumnConstraints().clear();
        courses_gridPane.getRowConstraints().clear();

        int row = 0, column = 0;

        for (int q = 0; q < result.getData().size(); q++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(GymApplication.class.getResource("CourseCard.fxml"));
                StackPane stack = loader.load();

                CourseCardController dController = loader.getController();
                dController.setData(result.getData().get(q));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                courses_gridPane.add(stack, column++, row);

                GridPane.setMargin(stack, new Insets(15));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMembershipCard() {
        int actionId = MemberMenuConstants.VIEW_MEMBERSHIP;
        ActionResult<MembershipCard> result = SessionManager.getInstance().getCurrentRole().performAction(actionId);
        if (result.isSuccess()) {
            MembershipCard membershipCard = result.getData();
            Platform.runLater(() -> {
                cur_card_name.setText(membershipCard.getType().getName());
                cur_card_begin_date.setText(membershipCard.getStartDate().toString());
                cur_card_end_date.setText(membershipCard.getEndDate().toString());
            });
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }

    @FXML
    private void switchForm(ActionEvent event) {
        home_form.setVisible(false);
        trainers_form.setVisible(false);
        appointments_form.setVisible(false);
        profile_form.setVisible(false);
        gymclasses_form.setVisible(false);

        // Show the form based on the button clicked
        if (event.getSource() == dashboard_btn) {
            home_form.setVisible(true);
        } else if (event.getSource() == trainers_btn) {
            trainers_form.setVisible(true);
        } else if (event.getSource() == appointments_btn) {
            appointments_form.setVisible(true);
        } else if (event.getSource() == profile_btn) {
            profile_form.setVisible(true);
        } else if (event.getSource() == gymclasses_btn) {
            gymclasses_form.setVisible(true);
        }
    }

    @FXML
    private void appointmentBookBtn() {
        System.out.println("Appointment book button clicked");
    }

    @FXML
    private void appointmentConfirmBtn() {
        System.out.println("Appointment confirm button clicked");
    }

    @FXML
    private void appointmentClearBtn() {
        System.out.println("Appointment clear button clicked");
    }

    @FXML
    private void profileImportBtn() {
        System.out.println("Profile import button clicked");
    }

    @FXML
    private void profileUpdateBtn() {
        System.out.println("Profile update button clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserInfo();
        loadGymClass();
        loadPT();
        loadTrainerCard();
        loadCourseCard();
        loadMembershipCard();
    }
}

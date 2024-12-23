package com.framja.gymmanagement.controller;


import java.net.URL;

import java.util.ResourceBundle;

import com.framja.gymmanagement.model.GymClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import javafx.fxml.Initializable;

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
    private Label nav_adminID;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button doctors_btn;

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
    private Label home_doctor_name;

    @FXML
    private Label home_doctor_specialization;

    @FXML
    private Label home_doctor_email;

    @FXML
    private Label home_doctor_mobileNumber;

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
    private ScrollPane doctors_scrollPane;

    @FXML
    private GridPane doctors_gridPane;

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
    private AnchorPane home_form, doctors_form, appointments_form, profile_form;

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

    private void loadGymClass() {
        // Sample data
        ObservableList<GymClass> gymClasses = FXCollections.observableArrayList();
        GymClass gymClass1 = new GymClass(1L, "Yoga Basics", "08:00 AM", "09:00 AM", "Monday, Wednesday, Friday", 101, 20);
        GymClass gymClass2 = new GymClass(2L, "Strength Training", "10:00 AM", "11:00 AM", "Tuesday, Thursday", 102, 15);
        GymClass gymClass3 = new GymClass(3L, "Cardio Blast", "05:00 PM", "06:00 PM", "Monday to Friday", 103, 25);
        GymClass gymClass4 = new GymClass(4L, "Pilates", "07:00 AM", "08:00 AM", "Saturday, Sunday", 104, 10);
        GymClass gymClass5 = new GymClass(5L, "Zumba Dance", "06:00 PM", "07:00 PM", "Wednesday, Friday", 105, 30);

        gymClasses.addAll(gymClass1, gymClass2, gymClass3, gymClass4, gymClass5);

        gymClassColDescription.setCellValueFactory(new PropertyValueFactory<>("name")); // Assuming ID is appointment ID
        gymClassColCourse.setCellValueFactory(new PropertyValueFactory<>("name"));
        gymClassColTrainer.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        gymClassColSchedule.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        centerAlignColumnContent(gymClassColDescription);
        centerAlignColumnContent(gymClassColCourse);
        centerAlignColumnContent(gymClassColTrainer);
        centerAlignColumnContent(gymClassColSchedule);
        centerAlignColumnContent(gymClassColTime);

        // Set items in the table
        gymClassTableView.setItems(gymClasses);
    }

    private void loadPT() {
        ObservableList<GymClass> gymClasses = FXCollections.observableArrayList();
        GymClass gymClass1 = new GymClass(1L, "Yoga Basics", "08:00 AM", "09:00 AM", "Monday, Wednesday, Friday", 101, 20);
        GymClass gymClass2 = new GymClass(2L, "Strength Training", "10:00 AM", "11:00 AM", "Tuesday, Thursday", 102, 15);
        GymClass gymClass3 = new GymClass(3L, "Cardio Blast", "05:00 PM", "06:00 PM", "Monday to Friday", 103, 25);
        GymClass gymClass4 = new GymClass(4L, "Pilates", "07:00 AM", "08:00 AM", "Saturday, Sunday", 104, 10);
        GymClass gymClass5 = new GymClass(5L, "Zumba Dance", "06:00 PM", "07:00 PM", "Wednesday, Friday", 105, 30);

        gymClasses.addAll(gymClass1, gymClass2, gymClass3, gymClass4, gymClass5);

        ptColDescription.setCellValueFactory(new PropertyValueFactory<>("name")); // Assuming ID is appointment ID
        ptColDay.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        ptColTrainer.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        ptColTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        centerAlignColumnContent(ptColDescription);
        centerAlignColumnContent(ptColDay);
        centerAlignColumnContent(ptColTrainer);
        centerAlignColumnContent(ptColTime);

        // Set items in the table
        ptTableView.setItems(gymClasses);
    }

    @FXML
    private void switchForm(ActionEvent event) {
        home_form.setVisible(false);
        doctors_form.setVisible(false);
        appointments_form.setVisible(false);
        profile_form.setVisible(false);

        // Show the form based on the button clicked
        if (event.getSource() == dashboard_btn) {
            home_form.setVisible(true);
        } else if (event.getSource() == doctors_btn) {
            doctors_form.setVisible(true);
        } else if (event.getSource() == appointments_btn) {
            appointments_form.setVisible(true);
        } else if (event.getSource() == profile_btn) {
            profile_form.setVisible(true);
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
        loadGymClass();
        loadPT();
    }
}

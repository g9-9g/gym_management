package com.framja.gymmanagement.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainerDashboardController implements Initializable {

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
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    // Dashboard form
    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private TableView<?> dashboad_tableView;

    @FXML
    private TableColumn<?, ?> dashboad_col_appointmentID;

    @FXML
    private TableColumn<?, ?> dashboad_col_name;

    @FXML
    private TableColumn<?, ?> dashboad_col_description;

    @FXML
    private TableColumn<?, ?> dashboad_col_appointmentDate;

    @FXML
    private TableColumn<?, ?> dashboad_col_status;

    // Patients form
    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Button patients_PI_recordBtn;

    // Appointments form
    @FXML
    private TableView<?> appointments_tableView;

    @FXML
    private TableColumn<?, ?> appointments_col_appointmentID;

    @FXML
    private TableColumn<?, ?> appointments_col_name;

    @FXML
    private TableColumn<?, ?> appointments_col_gender;

    @FXML
    private TableColumn<?, ?> appointments_col_contactNumber;

    @FXML
    private TableColumn<?, ?> appointments_col_description;

    @FXML
    private TableColumn<?, ?> appointments_col_date;

    @FXML
    private TableColumn<?, ?> appointments_col_dateModify;

    @FXML
    private TableColumn<?, ?> appointments_col_dateDelete;

    @FXML
    private TableColumn<?, ?> appointments_col_status;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    // Profile form
    @FXML
    private Circle profile_circleImage;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialized;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Button profile_updateBtn;

    // Button handlers
    @FXML
    private void logoutBtn() {
        System.out.println("logoutBtn clicked");
    }

    @FXML
    private void switchForm() {
        System.out.println("Switching forms");
    }

    @FXML
    private void patientConfirmBtn() {
        System.out.println("patientConfirmBtn clicked");
    }

    @FXML
    private void patientAddBtn() {
        System.out.println("patientAddBtn clicked");
    }

    @FXML
    private void patientRecordBtn() {
        System.out.println("patientRecordBtn clicked");
    }

    @FXML
    private void appointmentInsertBtn() {
        System.out.println("appointmentInsertBtn clicked");
    }

    @FXML
    private void appointmentUpdateBtn() {
        System.out.println("appointmentUpdateBtn clicked");
    }

    @FXML
    private void appointmentClearBtn() {
        System.out.println("appointmentClearBtn clicked");
    }

    @FXML
    private void appointmentDeleteBtn() {
        System.out.println("appointmentDeleteBtn clicked");
    }

    @FXML
    private void profileChangeProfile() {
        System.out.println("profileChangeProfile clicked");
    }

    @FXML
    private void profileUpdateBtn() {
        System.out.println("profileUpdateBtn clicked");
    }

    @FXML
    private void appointmentSelect() {
        System.out.println("Appointment selected from table");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

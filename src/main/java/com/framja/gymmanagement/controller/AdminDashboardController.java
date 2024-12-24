package com.framja.gymmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class AdminDashboardController {

    // Top Section
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

    // Left Navigation Section
    @FXML
    private Label nav_adminID;
    @FXML
    private Label nav_username;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button doctors_btn;
    @FXML
    private Button patients_btn;
    @FXML
    private Button appointments_btn;
    @FXML
    private Button payment_btn;
    @FXML
    private Button profile_btn;

    // Dashboard Form
    @FXML
    private Label dashboard_AD;
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
    private TableColumn<?, ?> dashboad_col_doctorID;
    @FXML
    private TableColumn<?, ?> dashboad_col_name;
    @FXML
    private TableColumn<?, ?> dashboad_col_specialized;
    @FXML
    private TableColumn<?, ?> dashboad_col_status;

    // Doctors Form
    @FXML
    private TableView<?> doctors_tableView;
    @FXML
    private TableColumn<?, ?> doctors_col_doctorID;
    @FXML
    private TableColumn<?, ?> doctors_col_name;
    @FXML
    private TableColumn<?, ?> doctors_col_gender;
    @FXML
    private TableColumn<?, ?> doctors_col_contactNumber;
    @FXML
    private TableColumn<?, ?> doctors_col_email;
    @FXML
    private TableColumn<?, ?> doctors_col_specialization;
    @FXML
    private TableColumn<?, ?> doctors_col_address;
    @FXML
    private TableColumn<?, ?> doctors_col_status;
    @FXML
    private TableColumn<?, ?> doctors_col_action;

    // Patients Form
    @FXML
    private TableView<?> patients_tableView;
    @FXML
    private TableColumn<?, ?> patients_col_patientID;
    @FXML
    private TableColumn<?, ?> patients_col_name;
    @FXML
    private TableColumn<?, ?> patients_col_gender;
    @FXML
    private TableColumn<?, ?> patients_col_contactNumber;
    @FXML
    private TableColumn<?, ?> patients_col_description;
    @FXML
    private TableColumn<?, ?> patients_col_date;
    @FXML
    private TableColumn<?, ?> patients_col_dateModify;
    @FXML
    private TableColumn<?, ?> patients_col_dateDelete;
    @FXML
    private TableColumn<?, ?> patients_col_status;
    @FXML
    private TableColumn<?, ?> patients_col_action;

    // Appointments Form
    @FXML
    private TableView<?> appointments_tableView;
    @FXML
    private TableColumn<?, ?> appointments_appointmentID;
    @FXML
    private TableColumn<?, ?> appointments_name;
    @FXML
    private TableColumn<?, ?> appointments_gender;
    @FXML
    private TableColumn<?, ?> appointments_contactNumber;
    @FXML
    private TableColumn<?, ?> appointments_description;
    @FXML
    private TableColumn<?, ?> appointments_date;
    @FXML
    private TableColumn<?, ?> appointments_dateModify;
    @FXML
    private TableColumn<?, ?> appointments_dateDelete;
    @FXML
    private TableColumn<?, ?> appointments_status;
    @FXML
    private TableColumn<?, ?> appointments_action;

    // Payment Form
    @FXML
    private TableView<?> payment_tableView;
    @FXML
    private TableColumn<?, ?> payment_col_patientID;
    @FXML
    private TableColumn<?, ?> payment_col_name;
    @FXML
    private TableColumn<?, ?> payment_col_gender;
    @FXML
    private TableColumn<?, ?> payment_col_diagnosis;
    @FXML
    private TableColumn<?, ?> payment_col_doctor;
    @FXML
    private TableColumn<?, ?> payment_col_date;
    @FXML
    private Circle payment_circle;
    @FXML
    private Label payment_patientID;
    @FXML
    private Label payment_name;
    @FXML
    private Label payment_doctor;
    @FXML
    private Label payment_date;
    @FXML
    private Button payment_checkoutBtn;

    // Profile Form
    @FXML
    private Circle profile_circle;
    @FXML
    private Label profile_label_adminIO;
    @FXML
    private Label profile_label_name;
    @FXML
    private Label profile_label_email;
    @FXML
    private Label profile_label_dateCreated;
    @FXML
    private TextField profile_adminID;
    @FXML
    private TextField profile_username;
    @FXML
    private TextField profile_email;
    @FXML
    private ComboBox<String> profile_status;
    @FXML
    private Button profile_importBtn;
    @FXML
    private Button profile_updateBtn;

    // Function Definitions
    @FXML
    private void logoutBtn() {
        System.out.println("logoutBtn called");
    }

    @FXML
    private void switchForm() {
        System.out.println("switchForm called");
    }

    @FXML
    private void paymentSelectItems() {
        System.out.println("paymentSelectItems called");
    }

    @FXML
    private void paymentCheckOutBtn() {
        System.out.println("paymentCheckOutBtn called");
    }

    @FXML
    private void profileInsertImage() {
        System.out.println("profileInsertImage called");
    }

    @FXML
    private void profileUpdateBtn() {
        System.out.println("profileUpdateBtn called");
    }
}

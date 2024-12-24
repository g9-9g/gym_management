package com.framja.gymmanagement.controller;


import java.net.URL;

import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import com.framja.gymmanagement.GymApplication;
import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.constants.TrainerMenuConstants;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.*;

import com.framja.gymmanagement.role.Trainer;
import com.framja.gymmanagement.service.CourseServiceImpl;
import com.framja.gymmanagement.utils.ServiceContainer;
import com.framja.gymmanagement.utils.SessionManager;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


import javafx.fxml.FXMLLoader;



import java.util.List;

import javafx.geometry.Insets;

import javafx.scene.layout.StackPane;



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
    private TableColumn<GymClass, String> gymClassColTrainer;

    @FXML
    private TableColumn<GymClass, String> gymClassColSchedule;


    @FXML
    private Circle card_circle;

    @FXML
    private Label cur_card_name;

    @FXML
    private Label cur_card_begin_date;

    @FXML
    private Label cur_card_end_date;


    @FXML
    private TableView<Payment> ptTableView;

    @FXML
    private TableColumn<Payment, String> ptColDescription;

    @FXML
    private TableColumn<Payment, String> ptColPaymentId;

    @FXML
    private TableColumn<Payment, String> ptColAmount;

    @FXML
    private TableColumn<Payment, String> ptColTime;

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

    @FXML
    private ComboBox<MembershipCardType> select_card;

    @FXML
    private Button registration;

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


    // Implements
    private UserService userService;
    private CourseService courseService;
    private ClassService classService;

    private <S, T> void centerAlignColumnContent(TableColumn<S, T> column) {
        column.setCellFactory(tc -> {
            TableCell<S, T> cell = new TableCell<>() {
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
        User cur = SessionManager.getInstance().getCurrentUser();

        if (cur != null) {
            nav_username.setText(cur.getUsername());
            top_username.setText(cur.getUsername());
        } else {
            System.out.println("Error loading User Information");
        }

    }

    private void loadGymClass() {
//        gymClassTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        int actionId = TrainerMenuConstants.VIEW_TRAINER_CLASSES;
        ActionResult<List<GymClass>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);


        if (result.isSuccess()) {
            System.out.println("Result: " + result.getData());
//            gymClassColDescription.setCellValueFactory(new PropertyValueFactory<>("id"));
            gymClassColCourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
            gymClassColTrainer.setCellValueFactory(new PropertyValueFactory<>("instructor"));
            gymClassColSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));

            System.out.println(courseService.findAllCourses());

            gymClassColDescription.setCellValueFactory(cellData -> {
                Course cur_course = courseService.findCourseById(cellData.getValue().getCourseId()).orElse(null);
                return new SimpleStringProperty(cur_course != null ? cur_course.getDescription() : "");
            });

            gymClassColTrainer.setCellValueFactory(cellData -> {
                User instructor = cellData.getValue().getInstructor();
                return new SimpleStringProperty(instructor != null ? instructor.getUsername() : "");
            });

            centerAlignColumnContent(gymClassColDescription);
            centerAlignColumnContent(gymClassColCourse);
            centerAlignColumnContent(gymClassColTrainer);
            centerAlignColumnContent(gymClassColSchedule);

            gymClassColCourse.prefWidthProperty().bind(gymClassTableView.widthProperty().multiply(0.2)); // 20%
            gymClassColDescription.prefWidthProperty().bind(gymClassTableView.widthProperty().multiply(0.4)); // 40%
            gymClassColTrainer.prefWidthProperty().bind(gymClassTableView.widthProperty().multiply(0.2)); // 20%
            gymClassColSchedule.prefWidthProperty().bind(gymClassTableView.widthProperty().multiply(0.2));

            gymClassTableView.setItems(FXCollections.observableArrayList(result.getData()));
        } else {
            System.out.println("Error: " + result.getMessage());
        }


    }

    private void loadPT() {
        int actionId = TrainerMenuConstants.VIEW_RECEIVED_PAYMENTS;
        ActionResult<List<Payment>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);

        if (result.isSuccess()) {
            // Set up columns to bind to Payment properties
            ptColPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
            ptColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//            ptColAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//            ptColTime.setCellValueFactory(new PropertyValueFactory<>("date"));

            ptColAmount.setCellValueFactory(cellData -> {
                double amount = cellData.getValue().getAmount(); // Assuming getAmount returns double
                return new SimpleStringProperty(String.format("%.2f", amount));
            });

            // Custom CellValueFactory for date (format LocalDate to string)
            ptColTime.setCellValueFactory(cellData -> {
                LocalDate date = cellData.getValue().getDate(); // Assuming getDate returns LocalDate
                return new SimpleStringProperty(date != null ? date.toString() : ""); // Format as needed
            });
            centerAlignColumnContent(ptColPaymentId);
            centerAlignColumnContent(ptColDescription);
            centerAlignColumnContent(ptColAmount);
            centerAlignColumnContent(ptColTime);

            // Populate the table
            ptTableView.setItems(FXCollections.observableArrayList(result.getData()));
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }




    public void loadCourseCard () {
        int actionId = TrainerMenuConstants.VIEW_ALL_COURSES;
        ActionResult<List<Course>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);

        if (result.isSuccess()) {
            courses_gridPane.getChildren().clear();
            courses_gridPane.getColumnConstraints().clear();
            courses_gridPane.getRowConstraints().clear();

            System.out.println(result.getData());

            int row = 0, column = 0;

            List <Course> courses = result.getData();

            for (int q = 0; q < courses.size(); q++) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(GymApplication.class.getResource("CourseCard.fxml"));
                    StackPane stack = loader.load();

                    // Load classes -> course
                    List <GymClass> classes = classService.getClassesByCourseId(courses.get(q).getId());

                    CourseCardController dController = loader.getController();

                    dController.setData(courses.get(q), classes);

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
        } else {
            System.out.println("Error: " + result.getMessage());
        }

    }



    @FXML
    private void logoutBtn() {
        SessionManager.getInstance().clearSession();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(GymApplication.class.getResource("admin-portal.fxml"));
    }

    @FXML
    private void switchForm(ActionEvent event) {
        home_form.setVisible(false);
        profile_form.setVisible(false);
        gymclasses_form.setVisible(false);

        // Show the form based on the button clicked
        if (event.getSource() == dashboard_btn) {
            loadGymClass();
            home_form.setVisible(true);
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
        userService = ServiceContainer.getInstance().getService(UserService.class);
        courseService = ServiceContainer.getInstance().getService(CourseService.class);
        classService = ServiceContainer.getInstance().getService(ClassService.class);

        loadUserInfo();
        loadGymClass();
        loadPT();
        loadCourseCard();
    }
}

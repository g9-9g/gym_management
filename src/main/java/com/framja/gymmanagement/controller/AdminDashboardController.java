package com.framja.gymmanagement.controller;


import java.net.URL;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.framja.gymmanagement.constants.AdminMenuConstants;
import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.constants.TrainerMenuConstants;
import com.framja.gymmanagement.interfaces.ClassService;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.*;

import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;
import com.framja.gymmanagement.utils.ServiceContainer;
import com.framja.gymmanagement.utils.SessionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;


import java.util.List;
import java.util.stream.Collectors;


public class AdminDashboardController implements Initializable {

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


    // Member
    @FXML
    private TableView<User> member_table;

    @FXML
    private TableColumn<User, String> memberColusername;

    @FXML
    private TableColumn<User, String> memberColpassword;

    @FXML
    private TableColumn<User, String> memberColphoneNumber;

    @FXML
    private TableColumn<User, String> memberColgender;

    @FXML
    private TableColumn<User, String> memberColaddress;

    @FXML
    private  TableColumn <User, String> editColumn;

    @FXML
    private TableColumn<User, String> deleteColumn;

    @FXML
    private ComboBox<MembershipCardType> select_card;

    @FXML
    private Button registration;


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
    private AnchorPane member_form, trainer_form, home_form;


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

    private void loadMember() {
        int actionId = AdminMenuConstants.FIND_ALL_USERS;
        ActionResult<List<User>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);

        if (result.isSuccess()) {
            List<User> usr = result.getData();

            List<User> members = usr.stream()
                    .filter(user -> user.getRole() == RoleType.MEMBER)
                    .collect(Collectors.toList());

            memberColusername.setCellValueFactory(new PropertyValueFactory<>("username"));
            memberColpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            memberColphoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            memberColgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            memberColaddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            centerAlignColumnContent(memberColusername);
            centerAlignColumnContent(memberColpassword);
            centerAlignColumnContent(memberColphoneNumber);
            centerAlignColumnContent(memberColgender);
            centerAlignColumnContent(memberColaddress);

            // Thiết lập CellFactory cho nút Edit
            editColumn.setCellFactory(col -> new TableCell<>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setOnAction(event -> {
                        User member = getTableView().getItems().get(getIndex());
                        handleEdit(member);
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            });

            // Thiết lập CellFactory cho nút Delete
            deleteColumn.setCellFactory(col -> new TableCell<>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        User member = getTableView().getItems().get(getIndex());
                        handleDelete(member);
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            });

            // Gán dữ liệu vào bảng
            member_table.setItems(FXCollections.observableArrayList(members));
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }

    private void handleEdit(User member) {
        System.out.println("Edit button clicked for: " + member.getUsername());
        // TODO: Hiển thị một form hoặc dialog để chỉnh sửa thông tin thành viên
        // Bạn có thể sử dụng Stage hoặc Dialog để hiển thị form chỉnh sửa.
    }

    private void handleDelete(User member) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete member: " + member.getUsername() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            member_table.getItems().remove(member);
            System.out.println("Deleted member: " + member.getUsername());
            // TODO: Thực hiện xóa thành viên khỏi database (nếu cần)
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






    @FXML
    private void logoutBtn() {
        SessionManager.getInstance().clearSession();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(GymApplication.class.getResource("admin-portal.fxml"));
    }

    @FXML
    private void switchForm(ActionEvent event) {
//        home_form.setVisible(false);
//        member_form.setVisible(false);
//        trainer_form.setVisible(false);
//
//        // Show the form based on the button clicked
//        if (event.getSource() == dashboard_btn) {
////            loadMember();
//            member_form.setVisible(true);
//        } else if (event.getSource() == profile_btn) {
//            member_form.setVisible(true);
//        } else if (event.getSource() == gymclasses_btn) {
//            member_form.setVisible(true);
//        }
        member_form.setVisible(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = ServiceContainer.getInstance().getService(UserService.class);
        courseService = ServiceContainer.getInstance().getService(CourseService.class);
        classService = ServiceContainer.getInstance().getService(ClassService.class);

        loadUserInfo();
        loadMember();
//        loadPT();
    }
}

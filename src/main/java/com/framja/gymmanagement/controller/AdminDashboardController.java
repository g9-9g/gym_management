package com.framja.gymmanagement.controller;


import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.framja.gymmanagement.GymApplication;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
    private Button equipment_btn;
    @FXML
    private Button course_btn;

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
    private TableView<User> trainer_table;

    @FXML
    private TableColumn<User, String> trainerColusername;

    @FXML
    private TableColumn<User, String> trainerColpassword;

    @FXML
    private TableColumn<User, String> trainerColphoneNumber;

    @FXML
    private TableColumn<User, String> trainerColgender;

    @FXML
    private TableColumn<User, String> trainerColaddress;

    @FXML
    private  TableColumn <User, String> editTrainerColumn;

    @FXML
    private TableColumn<User, String> deleteTrainerColumn;

    @FXML
    private ComboBox<MembershipCardType> select_card;

    @FXML
    private Button registration;

    @FXML
    private TableView<Equipment> equipment_table;

    @FXML
    private TableColumn<Equipment, Integer> equipmentColId;

    @FXML
    private TableColumn<Equipment, String> equipmentColName;

    @FXML
    private TableColumn<Equipment, String> equipmentColCategory;

    @FXML
    private TableColumn<Equipment, Double> equipmentColPrice;

    @FXML
    private TableColumn<Equipment, String> equipmentColStatus;

    @FXML
    private  TableColumn <Equipment, String> editEquipmentColumn;

    @FXML
    private TableColumn<Equipment, String> deleteEquipmentColumn;

    @FXML
    private TableView<Course> course_table;

    @FXML
    private TableColumn<Course, Integer> courseColId;

    @FXML
    private TableColumn<Course, String> courseColName;

    @FXML
    private TableColumn<Course, Double> courseColPrice;

    @FXML
    private TableColumn<Course, Integer> courseColMaxMembers;

    @FXML
    private TableColumn<Course, String> courseColStartDate;

    @FXML
    private  TableColumn <User, String> editCourseColumn;

    @FXML
    private TableColumn<User, String> deleteCourseColumn;

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
    private AnchorPane member_form, trainer_form, equipment_form, course_form;


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



    private void loadTrainer() {
        int actionId = AdminMenuConstants.FIND_ALL_USERS; // Giả sử actionId cho trainers là FIND_ALL_TRAINERS
        ActionResult<List<User>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);

        if (result.isSuccess()) {
            List<User> usr = result.getData();

            // Lọc danh sách chỉ bao gồm các trainer
            List<User> trainers = usr.stream()
                    .filter(user -> user.getRole() == RoleType.TRAINER)
                    .collect(Collectors.toList());

            // Thiết lập CellValueFactory cho các cột
            trainerColusername.setCellValueFactory(new PropertyValueFactory<>("username"));
            trainerColpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            trainerColphoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            trainerColgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            trainerColaddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            // Canh giữa nội dung các cột
            centerAlignColumnContent(trainerColusername);
            centerAlignColumnContent(trainerColpassword);
            centerAlignColumnContent(trainerColphoneNumber);
            centerAlignColumnContent(trainerColgender);
            centerAlignColumnContent(trainerColaddress);

            // Thiết lập CellFactory cho nút Edit
            editTrainerColumn.setCellFactory(col -> new TableCell<>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setOnAction(event -> {
                        User trainer = getTableView().getItems().get(getIndex());
                        handleEdit(trainer); // Gọi hàm xử lý Edit cho trainer
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
            deleteTrainerColumn.setCellFactory(col -> new TableCell<>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        User trainer = getTableView().getItems().get(getIndex());
                        handleDeleteTrainer(trainer); // Gọi hàm xử lý Delete cho trainer
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
            trainer_table.setItems(FXCollections.observableArrayList(trainers));
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }

    public void handleAddTrainer() {
//        System.out.println("Edit button clicked for: " + member.getUsername());
        // TODO: Hiển thị một form hoặc dialog để chỉnh sửa thông tin thành viên
        // Tải file FXML
        FXMLLoader loader = new FXMLLoader(GymApplication.class.getResource("add-member-form.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EditMemberFormController controller = loader.getController();


        // Tạo Stage mới
        Stage stage = new Stage();
        stage.setTitle("Add Trainer");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // Đặt chế độ modal (chặn tương tác với cửa sổ khác)
        stage.showAndWait(); // Đợi người dùng hoàn tất chỉnh sửa


        trainer_table.getItems().add(controller.getMember());

        // Sau khi form đóng, cập nhật bảng (nếu cần)
        trainer_table.refresh();
    }
    private void loadEquipment() {
        int actionId = AdminMenuConstants.VIEW_ALL_EQUIPMENT; // Giả sử actionId là FIND_ALL_EQUIPMENT
        ActionResult<List<Equipment>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);

        if (result.isSuccess()) {
            List<Equipment> equipmentList = result.getData();

            // Thiết lập CellValueFactory cho các cột
            equipmentColId.setCellValueFactory(new PropertyValueFactory<>("id"));
            equipmentColName.setCellValueFactory(new PropertyValueFactory<>("name"));
            equipmentColCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            equipmentColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            equipmentColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            // Canh giữa nội dung các cột
            centerAlignColumnContent(equipmentColId);
            centerAlignColumnContent(equipmentColName);
            centerAlignColumnContent(equipmentColCategory);
            centerAlignColumnContent(equipmentColPrice);
            centerAlignColumnContent(equipmentColStatus);

            // Thiết lập CellFactory cho nút Edit
            editEquipmentColumn.setCellFactory(col -> new TableCell<>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setOnAction(event -> {
                        Equipment equipment = getTableView().getItems().get(getIndex());
//                        handleEditEquipment(equipment); // Gọi hàm xử lý Edit cho equipment
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
            deleteEquipmentColumn.setCellFactory(col -> new TableCell<>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Equipment equipment = getTableView().getItems().get(getIndex());
//                        handleDeleteEquipment(equipment); // Gọi hàm xử lý Delete cho equipment
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
            equipment_table.setItems(FXCollections.observableArrayList(equipmentList));
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }

    private void loadCourse() {
        int actionId = AdminMenuConstants.READ_ALL_COURSES; // Giả sử actionId là VIEW_ALL_COURSES
        ActionResult<List<Course>> result = SessionManager.getInstance().getCurrentUser().performAction(actionId);

        if (result.isSuccess()) {
            List<Course> courseList = result.getData();

            // Thiết lập CellValueFactory cho các cột
            courseColId.setCellValueFactory(new PropertyValueFactory<>("id"));
            courseColName.setCellValueFactory(new PropertyValueFactory<>("name"));
            courseColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            courseColMaxMembers.setCellValueFactory(new PropertyValueFactory<>("maxMembers"));
            courseColStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

            // Canh giữa nội dung các cột
            centerAlignColumnContent(courseColId);
            centerAlignColumnContent(courseColName);
            centerAlignColumnContent(courseColPrice);
            centerAlignColumnContent(courseColMaxMembers);
            centerAlignColumnContent(courseColStartDate);

            // Thiết lập CellFactory cho nút Edit
            editCourseColumn.setCellFactory(col -> new TableCell<>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setOnAction(event -> {
//                        Course course = getTableView().getItems().get(getIndex());
//                        handleEditCourse(course); // Gọi hàm xử lý Edit cho khóa học
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
            deleteCourseColumn.setCellFactory(col -> new TableCell<>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
//                        Course course = getTableView().getItems().get(getIndex());
//                        handleDeleteCourse(course); // Gọi hàm xử lý Delete cho khóa học
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
            course_table.setItems(FXCollections.observableArrayList(courseList));
        } else {
            System.out.println("Error: " + result.getMessage());
        }
    }

    public void handleAdd() {
//        System.out.println("Edit button clicked for: " + member.getUsername());
        // TODO: Hiển thị một form hoặc dialog để chỉnh sửa thông tin thành viên
        // Tải file FXML
        FXMLLoader loader = new FXMLLoader(GymApplication.class.getResource("add-member-form.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EditMemberFormController controller = loader.getController();


        // Tạo Stage mới
        Stage stage = new Stage();
        stage.setTitle("Add Member");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // Đặt chế độ modal (chặn tương tác với cửa sổ khác)
        stage.showAndWait(); // Đợi người dùng hoàn tất chỉnh sửa


        member_table.getItems().add(controller.getMember());

        // Sau khi form đóng, cập nhật bảng (nếu cần)
        member_table.refresh();
    }

    public void handleEdit(User member) {
        System.out.println("Edit button clicked for: " + member.getUsername());
        // TODO: Hiển thị một form hoặc dialog để chỉnh sửa thông tin thành viên
        // Tải file FXML
        FXMLLoader loader = new FXMLLoader(GymApplication.class.getResource("edit-member-form.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Lấy controller của form chỉnh sửa
        EditMemberFormController controller = loader.getController();
        controller.setMember(member); // Truyền dữ liệu thành viên cần chỉnh sửa

        // Tạo Stage mới
        Stage stage = new Stage();
        stage.setTitle("Edit" + member.getRole().toString());
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // Đặt chế độ modal (chặn tương tác với cửa sổ khác)
        stage.showAndWait(); // Đợi người dùng hoàn tất chỉnh sửa

        // Sau khi form đóng, cập nhật bảng (nếu cần)
        member_table.refresh();
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

//            ActionResult<User> result = SessionManager.getInstance().getCurrentUser().performAction(AdminMenuConstants.D, new_member);
//            System.out.println(result.getMessage());
        }
    }

    private void handleDeleteTrainer(User member) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete member: " + member.getUsername() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            trainer_table.getItems().remove(member);
            System.out.println("Deleted member: " + member.getUsername());
            // TODO: Thực hiện xóa thành viên khỏi database (nếu cần)

//            ActionResult<User> result = SessionManager.getInstance().getCurrentUser().performAction(AdminMenuConstants.D, new_member);
//            System.out.println(result.getMessage());
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
        FXMLLoader loader = new FXMLLoader(GymApplication.class.getResource("admin-portal.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get the current stage and replace it with the login screen
        Stage stage = (Stage) logout_btn.getScene().getWindow(); // Replace `logout_btn` with the correct button ID
        stage.setTitle("Admin Portal");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void switchForm(ActionEvent event) {
        equipment_form.setVisible(false);
        member_form.setVisible(false);
        trainer_form.setVisible(false);
        course_form.setVisible(false);

        // Show the form based on the button clicked
        if (event.getSource() == dashboard_btn) {
//            loadMember();
            member_form.setVisible(true);
        } else if (event.getSource() == trainers_btn) {
            trainer_form.setVisible(true);
        } else if (event.getSource() == equipment_btn) {
            equipment_form.setVisible(true);
        } else if (event.getSource() == course_btn) {
            course_form.setVisible(true);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadUserInfo();
        loadMember();
        loadTrainer();
        loadEquipment();
        loadCourse();
    }
}

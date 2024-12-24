package com.framja.gymmanagement.controller;

import com.framja.gymmanagement.constants.AdminMenuConstants;
import com.framja.gymmanagement.model.ActionResult;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditMemberFormController {

    @FXML
    private TextField edit_patientID; // ID của thành viên

    @FXML
    private TextField edit_name; // Tên thành viên

    @FXML
    private ComboBox<String> edit_gender; // Giới tính

    @FXML
    private TextField edit_contactNumber; // Số điện thoại

    @FXML
    private TextArea edit_address; // Địa chỉ


    @FXML
    private Button edit_updateBtn; // Nút cập nhật

    private User member; // Đối tượng thành viên cần chỉnh sửa

    private User new_member = new User("hello", "123", RoleType.MEMBER);
    /**
     * Thiết lập thành viên cần chỉnh sửa và điền dữ liệu vào form.
     *
     * @param member đối tượng thành viên
     */
    public void setMember(User member) {
        this.member = member;

        // Điền dữ liệu thành viên vào các trường
        // edit_patientID.setText(String.valueOf(member.getId())); // Hiển thị ID (giả sử Member có thuộc tính ID)
        edit_name.setText(member.getUsername()); // Hiển thị tên
        edit_gender.setValue(member.getGender()); // Hiển thị giới tính
        edit_contactNumber.setText(member.getPhoneNumber()); // Hiển thị số điện thoại
        edit_address.setText(member.getAddress()); // Hiển thị địa chỉ
    }

    public User getMember() {
        return new_member;
    }

    @FXML
    private void updateBtn() {
        // Cập nhật thông tin từ form vào đối tượng thành viên
//        member.setPassword(edit_name.getText());
        member.setGender(edit_gender.getValue());
        member.setPhoneNumber(edit_contactNumber.getText());
        member.setAddress(edit_address.getText());


        ActionResult<User> result = SessionManager.getInstance().getCurrentUser().performAction(AdminMenuConstants.UPDATE_MEMBER, new_member);
        System.out.println(result.getMessage());
        // Đóng cửa sổ
        Stage stage = (Stage) edit_updateBtn.getScene().getWindow();
        stage.close();

        System.out.println("Updated Member: " + member);
        // TODO: Thực hiện lưu thông tin đã chỉnh sửa vào database nếu cần
    }
    @FXML
    private void addBtn() {
        // Cập nhật thông tin từ form vào đối tượng thành viên
        new_member.setPassword(edit_name.getText());
        new_member.setGender(edit_gender.getValue());
        new_member.setPhoneNumber(edit_contactNumber.getText());
        new_member.setAddress(edit_address.getText());

        ActionResult<User> result = SessionManager.getInstance().getCurrentUser().performAction(AdminMenuConstants.ADD_USER, new_member);
        System.out.println(result.getMessage());
        // Đóng cửa sổ
        Stage stage = (Stage) edit_updateBtn.getScene().getWindow();
        stage.close();

        // TODO: Thực hiện lưu thông tin đã chỉnh sửa vào database nếu cần
    }

}

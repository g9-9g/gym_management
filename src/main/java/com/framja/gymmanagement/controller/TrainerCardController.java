package com.framja.gymmanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import com.framja.gymmanagement.model.Trainer;

/**
 *
 * @author WINDOWS 10
 */
public class TrainerCardController implements Initializable {

    @FXML
    private Circle trainer_circle;

    @FXML
    private Label trainer_id;

    @FXML
    private Label trainer_fullName;

    @FXML
    private Label trainer_specialization;


    private Image image;

    public void setData(Trainer data) {
            System.out.println("setData");
//        if (data.getImage() != null) {
//            image = new Image("File:" + data.getImage(), 52, 52, false, true);
//            doctor_circle.setFill(new ImagePattern(image));
//        }
//
//        doctor_id.setText(data.getDoctorID());
//        doctor_fullName.setText(data.getFullName());
//        doctor_specialization.setText(data.getSpecialized());
//        doctor_email.setText(data.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
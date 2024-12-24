package com.framja.gymmanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.framja.gymmanagement.GymApplication;
import com.framja.gymmanagement.role.Trainer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author WINDOWS 10
 */
public class TrainerCardController implements Initializable {

    @FXML
    private Circle trainer_circle;

    @FXML
    private Label trainer_phone_number;

    @FXML
    private Label trainer_name;

    @FXML
    private Label trainer_address;

    @FXML
    private Label trainer_specialization;


    private Image image;

    public void setData(Trainer data) {
        if (data.getImageUrl() != null) {
            Image image = new Image(GymApplication.class.getResource(data.getImageUrl()).toExternalForm());
            trainer_circle.setFill(new ImagePattern(image));

        }


        trainer_name.setText(data.getUsername());
        trainer_specialization.setText(data.getSpecialization());
        trainer_phone_number.setText(data.getPhoneNumber());
        trainer_address.setText(data.getAddress());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
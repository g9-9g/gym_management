package com.framja.gymmanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.framja.gymmanagement.role.Trainer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

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
    private Label trainer_name;

    @FXML
    private Label trainer_email;

    @FXML
    private Label trainer_specialization;


    private Image image;

    public void setData(Trainer data) {
//        if (data.getImage() != null) {
//            image = new Image("File:" + data.getImage(), 52, 52, false, true);
//            trainer_circle.setFill(new ImagePattern(image));
//        }

//        trainer_id.setText(data.getId());
//        trainer_name.setText(data.getName());
//        trainer_specialization.setText(data.getSpecialization());
//        trainer_email.setText(data.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
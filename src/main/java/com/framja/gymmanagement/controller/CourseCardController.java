package com.framja.gymmanagement.controller;


import java.net.URL;
import java.util.ResourceBundle;

import com.framja.gymmanagement.model.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 *
 * @author WINDOWS 10
 */
public class CourseCardController implements Initializable {

    @FXML
    private Circle course_circle;

    @FXML
    private Label course_id;

    @FXML
    private Label course_name;

    @FXML
    private Label course_number_class;

    @FXML
    private Label course_start_time;

    @FXML
    private Label course_end_time;

    private Image image;

    public void setData(Course data) {
//        if (data.getImage() != null) {
//            image = new Image("File:" + data.getImage(), 52, 52, false, true);
//            trainer_circle.setFill(new ImagePattern(image));
//        }
//
        course_id.setText(data.getName());
        course_name.setText(data.getDescription());
        course_number_class.setText(String.valueOf(data.getMaxMembers()));
        course_start_time.setText(data.getStartDate());
        course_end_time.setText(data.getEndDate());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

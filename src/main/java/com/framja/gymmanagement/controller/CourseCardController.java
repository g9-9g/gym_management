package com.framja.gymmanagement.controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.framja.gymmanagement.GymApplication;
import com.framja.gymmanagement.model.Course;
import com.framja.gymmanagement.model.GymClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    private List<GymClass> gymClassList;

    private Course courseInfo;

    public void setData(Course course, List<GymClass> classes) {
//        if (course.getImage() != null) {
//            image = new Image("File:" + course.getImage(), 52, 52, false, true);
//            trainer_circle.setFill(new ImagePattern(image));
//        }
//
        gymClassList = classes;
        courseInfo = course;

        course_id.setText(course.getName());
        course_name.setText(course.getDescription());
        course_number_class.setText(String.valueOf(classes.size()));
        course_start_time.setText(course.getStartDate());
        course_end_time.setText(course.getEndDate());
    }


    public void showClasses() {
        try {
            // Load the FXML for the popup
            FXMLLoader loader = new FXMLLoader(GymApplication.class.getResource("view-course.fxml"));
            Parent root = loader.load();

            ViewCourseController controller = loader.getController();
            controller.loadCourseInfo(courseInfo, gymClassList);

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            popupStage.setTitle("View Classes");
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

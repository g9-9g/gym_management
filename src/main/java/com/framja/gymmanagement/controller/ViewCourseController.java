package com.framja.gymmanagement.controller;

import com.framja.gymmanagement.constants.MemberMenuConstants;
import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.constants.TrainerMenuConstants;
import com.framja.gymmanagement.model.*;
import com.framja.gymmanagement.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ViewCourseController {

    @FXML
    private VBox gymClassContainer;

    @FXML
    private Label course_name;

    // Load the list of GymClass objects
    public void loadCourseInfo(Course course, List<GymClass> gymClasses) {
        gymClassContainer.getChildren().clear(); // Clear existing content
        course_name.setText(course.getName());


        int i = 1;
        for (GymClass gymClass : gymClasses) {
            // Create a card for each GymClass
            HBox card = createGymClassCard(i, gymClass);
            gymClassContainer.getChildren().add(card);
            i++;
        }
    }

    // Create a single GymClass card
    private HBox createGymClassCard(int index, GymClass gymClass) {
        HBox card = new HBox(10); // Horizontal layout for the card
        card.setPrefWidth(580);
        card.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label id = new Label (String.valueOf(index));
        Label schedule = new Label("Schedule: " + gymClass.getSchedule());
        Label trainer = new Label("Trainer: " + (gymClass.getInstructor() != null ? gymClass.getInstructor().getUsername() : "N/A"));

        id.setPrefWidth(50);
        schedule.setPrefWidth(300);
        trainer.setPrefWidth(150);

        // Enroll button
        Button enrollButton = new Button("Enroll");
        enrollButton.setOnAction(event -> enrollInClass(gymClass));
        enrollButton.getStyleClass().add("btn-3");

        enrollButton.setPrefWidth(100.0);
        // Add elements to the card
        card.getChildren().addAll(id, schedule, trainer, enrollButton);

        return card;
    }

    // Action for enrolling in a GymClass
    private void enrollInClass(GymClass gymClass) {
        User cur = SessionManager.getInstance().getCurrentUser();
        if (cur.getRole() == RoleType.MEMBER) {
            int actionId = MemberMenuConstants.REGISTER_FOR_CLASS; // error
            ActionResult<Boolean> result = SessionManager.getInstance().getCurrentUser().performAction(actionId, gymClass.getId());
            if (result.isSuccess() && result.getData().equals(true)) {
                System.out.println("Enrolled in GymClass: " + gymClass.getId());
            } else {
                System.out.println("Error ");
            }
        } else if (cur.getRole() == RoleType.TRAINER) {
            int actionId = TrainerMenuConstants.ASSIGN_TRAINER_TO_CLASS;
            ActionResult<Boolean> result = SessionManager.getInstance().getCurrentUser().performAction(actionId, cur, gymClass.getId());
            if (result.isSuccess() && result.getData().equals(true)) {
                System.out.println("Enrolled in GymClass: " + gymClass.getId());
            } else {
                System.out.println(result.getMessage());
            }
        }

    }
}

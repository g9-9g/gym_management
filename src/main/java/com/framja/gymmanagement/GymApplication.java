package com.framja.gymmanagement;

import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.utils.DataInitializer;
import com.framja.gymmanagement.utils.ServiceContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class GymApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ServiceContainer serviceContainer = ServiceContainer.getInstance();
        DataInitializer.initializeData(serviceContainer);

        FXMLLoader fxmlLoader = new FXMLLoader(GymApplication.class.getResource("admin-portal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
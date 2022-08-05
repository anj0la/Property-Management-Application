package com.example.propertymanagementapplication;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 *  This class starts, initializes and shows the application.
 */
public class MainApplication extends Application {

    public static SidebarMenuController mainController = new SidebarMenuController();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("sidebar-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        mainController = fxmlLoader.getController();
        mainController.initializeApplication();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } // start

    public static void main(String[] args) {
        launch();
    } // main

} // class

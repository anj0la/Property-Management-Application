package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * This class contains the methods needed to navigate, initialize, and load information into the application.
 */
public class SidebarMenuController {

    @FXML
    private HBox root;

    @FXML
    private AnchorPane currentPage;

    @FXML
    private VBox sideNavigationBar;

    @FXML
    private MFXButton homeButton;

    @FXML
    private MFXButton reportButton;

    @FXML
    private MFXButton archiveButton;

    @FXML
    private MFXButton settingsButton;

    /**
     * Initializes the application by setting the "default" page to be the home page when the
     * @throws IOException - thrown if the fxml is not found
     */
    @FXML
    protected void initializeApplication() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-page.fxml"));
        currentPage.getChildren().add(fxmlLoader.load());
        // root.setStyle("-fx-background-image: url('grey-background.png');");
        // currentPage.setStyle("-fx-background-image: url('grey-background.png');");
        homeButton.setStyle("-fx-background-color: lightgrey;" + "-fx-alignment: CENTER_LEFT");
        homeButton.setDisable(true);
        // all the information from the database will be loaded here
        // and the table will be "reconstructed"
    } // initializeApplication

    /**
     * Changes the current page of the application to the home page.
     * @throws IOException - thrown if the fxml is not found
     */
    @FXML
    protected void onHomeButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("home-page.fxml");
        homeButton.setStyle("-fx-background-color: lightgrey;" + "-fx-alignment: CENTER_LEFT");
        homeButton.setDisable(true);
        System.out.println("The home button was clicked!");
        // might need to reload information from the database if the application
        // "restarts" when changing the current page (resets to the default layout)
    } // onHomeButtonClick

    /**
     * Changes the current page of the application to the report page.
     * @throws IOException - thrown if the fxml is not found
     */
    @FXML
    protected void onReportButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("report-page.fxml");
        reportButton.setStyle("-fx-background-color: lightgrey;" + "-fx-alignment: CENTER_LEFT");
        reportButton.setDisable(true);
        System.out.println("The report button was clicked!");
    } // onReportButtonClick

    /**
     * Changes the current page of the application to the archive page.
     * @throws IOException - thrown if the fxml is not found
     */
    @FXML
    protected void onArchiveButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("archive-page.fxml");
        archiveButton.setStyle("-fx-background-color: lightgrey;" + "-fx-alignment: CENTER_LEFT");
        archiveButton.setDisable(true);
        System.out.println("The archive button was clicked!");
    } // onArchiveButtonClick

    /**
     * Changes the current page of the application to the settings page.
     * @throws IOException - thrown if the fxml is not found
     */
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("settings-page.fxml");
        settingsButton.setStyle("-fx-background-color: lightgrey;" + "-fx-alignment: CENTER_LEFT");
        settingsButton.setDisable(true);
        System.out.println("The settings button was clicked!");
    } // onSettingsButtonClick

    /**
     * Changes the current page of the application.
     * @throws IOException - thrown if the fxml is not found
     */
    @FXML
    private void changeCurrentPage(String fxmlName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlName));
        currentPage.getChildren().clear();
        currentPage.getChildren().add(fxmlLoader.load());
    } // changeCurrentPage

    /**
     * Enables all the buttons on the sidebar.
     */
    @FXML
    private void enableAllButtons() {
        homeButton.setDisable(false);
        reportButton.setDisable(false);
        archiveButton.setDisable(false);
        settingsButton.setDisable(false);
        homeButton.setStyle("-fx-background-color: transparent;" + "-fx-alignment: CENTER_LEFT");
        reportButton.setStyle("-fx-background-color: transparent;" + "-fx-alignment: CENTER_LEFT");
        archiveButton.setStyle("-fx-background-color: transparent;" + "-fx-alignment: CENTER_LEFT");
        settingsButton.setStyle("-fx-background-color: transparent;" + "-fx-alignment: CENTER_LEFT");
    } // enableAllButtons


} // class

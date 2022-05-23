package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    @FXML
    protected void initializeApplication() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-page.fxml"));
        currentPage.getChildren().add(fxmlLoader.load());
    } // initializeApplication

    @FXML
    protected void onHomeButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("home-page.fxml");
        homeButton.setDisable(true);
        System.out.println("The home button was clicked!");
    } // onHomeButtonClick

    @FXML
    protected void onReportButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("report-page.fxml");
        reportButton.setDisable(true);
        System.out.println("The report button was clicked!");
    } // onReportButtonClick

    @FXML
    protected void onArchiveButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("archive-page.fxml");
        archiveButton.setDisable(true);
        System.out.println("The archive button was clicked!");
    } // onArchiveButtonClick

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        enableAllButtons();
        changeCurrentPage("settings-page.fxml");
        settingsButton.setDisable(true);
        System.out.println("The settings button was clicked!");
    } // onSettingsButtonClick

    @FXML
    public void changeCurrentPage(String fxmlName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlName));
        currentPage.getChildren().clear();
        currentPage.getChildren().add(fxmlLoader.load());
    } // changeCurrentPage
    
    @FXML
    public void enableAllButtons() {
        homeButton.setDisable(false);
        reportButton.setDisable(false);
        archiveButton.setDisable(false);
        settingsButton.setDisable(false);
    } // enableAllButtons

} // class

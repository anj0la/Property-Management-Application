package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class HomePageController {

    @FXML
    private MFXButton addButton;

    @FXML
    private MFXButton editButton;

    @FXML
    private TableView table;

    /**
     * Initializes the home page;
     */
    @FXML
    protected void initializeHomePage() {
        table.setEditable(false);
    } // initializeHomePage

    @FXML
    protected void showAddDialog() {
        System.out.println("You clicked on the add button!");
    } // showAddDialog

    @FXML
    protected void showEditDialog() {
        System.out.println("You clicked on the edit button!");
    } // showEditDialog

} // class

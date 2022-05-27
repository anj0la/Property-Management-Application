package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Date;

public class HomePageController {

    @FXML
    private MFXButton addButton;

    @FXML
    private MFXButton editButton;

    @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<Client, Date> dateJoinedColumn;

    @FXML
    private TableColumn<Client, String> clientNameColumn;

    @FXML
    private TableColumn<Client, String> tenantNameColumn;

    @FXML
    private TableColumn<Client, String> addressColumn;

    @FXML
    private TableColumn<Client, Double> rentColumn;

    @FXML
    private TableColumn<Client, Double> expensesColumn;

    @FXML
    private TableColumn<Client, Double> commissionColumn;

    @FXML
    private TableColumn<Client, Double> paymentColumn;

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

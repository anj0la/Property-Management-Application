package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class contains the methods needed to do anything in the report page, such as viewing the monthly and yearly
 * table.
 */
public class ReportPageController implements Initializable {

    @FXML
    private Button shareButton;

    @FXML
    private Button printButton;

    @FXML
    private ToggleButton settingsButton;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<Client, String> dateJoinedColumn;

    @FXML
    private TableColumn<Client, String> clientNameColumn;

    @FXML
    private TableColumn<Client, String> tenantNameColumn;

    @FXML
    private TableColumn<Client, String> addressColumn;

    @FXML
    private TableColumn<Client, BigDecimal> rentColumn;

    @FXML
    private TableColumn<Client, BigDecimal> expensesColumn;

    @FXML
    private TableColumn<Client, BigDecimal> commissionColumn;

    @FXML
    private TableColumn<Client, BigDecimal> paymentColumn;

    @FXML
    private Button monthlyReportButton;

    @FXML
    private Button yearlyReportButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Client> clientsFromDatabase;
        try {
            clientsFromDatabase = DatabaseConnector.getClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("dateJoined"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        tenantNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("tenantName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("propertyAddress"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("propertyRent"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("propertyExpenses"));
        commissionColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("commission"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("paymentToClient"));
        monthlyReportButton.setDisable(true);
        shareButton.setDisable(true);
        printButton.setDisable(true);
        shareButton.setVisible(false);
        printButton.setVisible(false);
        table.setItems(clientsFromDatabase);
    }

    @FXML
    private void onToggleButtonClicked(ActionEvent event) {
        if (settingsButton.isSelected()) {
            shareButton.setDisable(false);
            printButton.setDisable(false);
            shareButton.setVisible(true);
            printButton.setVisible(true);
        } else {
            shareButton.setDisable(true);
            printButton.setDisable(true);
            shareButton.setVisible(false);
            printButton.setVisible(false);
        }
    }  // onToggleButtonClicked

    @FXML
    private void createPDFFromTable() {

    }
    @FXML
    /**
     * Displays the current share options that the user can select.
     */
    private void displayShareOptions() {
    } // displayShareOptions

    @FXML
    /**
     * Creates a PDF that the user is able to print out.
     */
    private void displayPrintOption() {
    } // displayPrintOption
} // class

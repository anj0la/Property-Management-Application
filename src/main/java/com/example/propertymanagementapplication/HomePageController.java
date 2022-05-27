package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private MFXButton addButton;

    @FXML
    private MFXButton delButton;

    @FXML
    private MFXButton editButton;

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
    private TableColumn<Client, Double> rentColumn;

    @FXML
    private TableColumn<Client, Double> expensesColumn;

    @FXML
    private TableColumn<Client, Double> commissionColumn;

    @FXML
    private TableColumn<Client, Double> paymentColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("dateJoined"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        tenantNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("tenantName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("propertyAddress"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("propertyRent"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("propertyExpenses"));
        commissionColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("commission"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("paymentToClient"));
        testing();
    } // initialize

    private void testing() {
        Client aDummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        ObservableList<Client> clients = table.getItems();
        clients.add(aDummyClient);
        table.setItems(clients);
    }


    @FXML
    protected void showAddDialog() {
        System.out.println("You clicked on the add button!");
    } // showAddDialog

    @FXML
    protected void showEditDialog() {
        System.out.println("You clicked on the edit button!");
    } // showEditDialog


} // class

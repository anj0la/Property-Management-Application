package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button delButton;

    @FXML
    private ToggleButton editButton;

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
    private TableColumn<Client, Double> rentColumn;

    @FXML
    private TableColumn<Client, Double> expensesColumn;

    @FXML
    private TableColumn<Client, Double> commissionColumn;

    @FXML
    private TableColumn<Client, Double> paymentColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // contentPane.setStyle("-fx-background-image: url('grey-background.png');");
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("dateJoined"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        tenantNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("tenantName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("propertyAddress"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("propertyRent"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("propertyExpenses"));
        commissionColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("commission"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("paymentToClient"));
        addButton.setDisable(true);
        delButton.setDisable(true);
        addButton.setVisible(false);
        delButton.setVisible(false);
        addDummyClients();
    } // initialize

    /**
     * METHOD TO BE DELETED ONCE DATABASE HAS BEEN IMPLEMENTATION, PURELY FOR TESTING PURPOSES.
     */
    private void addDummyClients() {
        Client aDummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a1DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a2DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a3DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a4DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a5DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a6DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a7DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a8DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a9DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a10DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a11DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a12DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a13DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a14DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        Client a15DummyClient = new Client("05/26/2022", "Sarah Beets", "Joy Waters",
                "101 Milky Road", 550.50, 199.99, 0.10,
                550.50 * 0.9);
        ObservableList<Client> clients = table.getItems();
        clients.add(aDummyClient);
        clients.addAll(a1DummyClient, a2DummyClient, a3DummyClient, a4DummyClient, a5DummyClient, a6DummyClient,
                a7DummyClient, a8DummyClient, a9DummyClient, a10DummyClient, a11DummyClient, a12DummyClient,
                a13DummyClient, a14DummyClient, a15DummyClient);
        table.setItems(clients);
    } // addDummyClients

    @FXML
    protected void addClient() {
        // TODO - Create add client dialog.
    } // addClient

    @FXML
    protected void removeClient() {
        // TODO - Add error checks for the function.
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        displayConfirmationDialog();
        table.getItems().remove(selectedIndex);
    } // removeClient

    @FXML
    protected void displayConfirmationDialog() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        String text = "Are you sure you would like to delete this entry?";
        confirmationAlert.setTitle("Confirm");
        confirmationAlert.setContentText(text);
        Optional<ButtonType> answer = confirmationAlert.showAndWait();
        if ((answer.isPresent()) && (answer.get()) == ButtonType.OK) {
            confirmationAlert.close();
        }
    } // displayConfirmationDialog

    @FXML
    protected void onToggleButtonClicked(ActionEvent event) {
        if (editButton.isSelected()) {
            addButton.setDisable(false);
            delButton.setDisable(false);
            addButton.setVisible(true);
            delButton.setVisible(true);
        } else {
            addButton.setDisable(true);
            delButton.setDisable(true);
            addButton.setVisible(false);
            delButton.setVisible(false);
        }
    }  // onToggleButtonClicked

} // class

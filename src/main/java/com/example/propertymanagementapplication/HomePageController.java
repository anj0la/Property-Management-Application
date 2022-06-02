package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

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
    } // addClient

    @FXML
    protected void removeClient() {
        // TODO - Add delay between displaying confirmation dialog and error dialog.
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        boolean ifOkayButtonPressed = displayConfirmationDialog();
        if (selectedIndex == -1 && !ifOkayButtonPressed) {
            return;
        } else if (selectedIndex == -1 && ifOkayButtonPressed) {
            displayErrorDialog();
            return;
        } else {
            table.getItems().remove(selectedIndex);
        }
    } // removeClient

    @FXML
    private boolean displayConfirmationDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirm");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setGraphic(null);
        confirmationDialog.setResizable(false);
        confirmationDialog.setContentText("Delete this entry? Press OK to confirm.");
        Optional<ButtonType> answer = confirmationDialog.showAndWait();
        if ((answer.isPresent()) && (answer.get()) == ButtonType.OK) {
            confirmationDialog.close();
            return answer.isPresent();
        }
        return false;
    } // displayConfirmationDialog

    private void displayErrorDialog() {
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Error");
        errorDialog.setHeaderText(null);
        errorDialog.setGraphic(null);
        errorDialog.setResizable(false);
        errorDialog.setContentText("No entry in the table was selected to be deleted!");
        Optional<ButtonType> buttonPressed = errorDialog.showAndWait();
        if (buttonPressed.isPresent()) {
            errorDialog.close();
        }
    } // displayErrorDialog

    @FXML
    private void displayAddClientDialog() {
        Dialog<Client> addDialog = new AddDialog(new Client());
        Optional<Client> result = addDialog.showAndWait();
        if (result.isPresent()) {
            Client newClient = result.get();
            newClient.setCommission(0.10);
            newClient.setPaymentToClient(newClient.getPropertyRent() - newClient.getCommission());
            ObservableList<Client> clients = table.getItems();
            clients.add(newClient);
            table.setItems(clients);
        }

        Label dateJoined = new Label("Date Joined: ");
        Label newClientName = new Label("Client Name: ");
        Label newTenantName = new Label("Tenant Name: ");
        Label newAddress = new Label("Address: ");
        Label newRent = new Label("Rent: ");
        Label newExpenses = new Label("Expenses: ");
        TextField dateInput = new TextField();
        TextField clientNameInput = new TextField();
        TextField tenantNameInput = new TextField();
        TextField addressInput = new TextField();
        TextField rentInput = new TextField();
        TextField expensesInput = new TextField();






    } // displayAddClientDialog

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

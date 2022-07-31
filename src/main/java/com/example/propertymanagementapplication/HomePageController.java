package com.example.propertymanagementapplication;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controllers the home page. The methods presented below help with the functionality of the home page,
 * such as adding, deleting and editing table entries.
 */
public class HomePageController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private Button delButton;
    @FXML
    private Button editButton;
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
    private Label totalRentLabel;
    @FXML
    private Label totalExpensesLabel;
    @FXML
    private Label totalCommissionLabel;
    @FXML
    private Label totalClientPaymentLabel;

    /**
     * Initializes the home page.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Client> clientsFromDatabase;
        ObservableList<MonthlyTotals> monthlyTotalsFromDatabase;
        try {
            clientsFromDatabase = DatabaseConnector.getClients();
            DatabaseConnector.handleMonthlyTotals();
            monthlyTotalsFromDatabase = DatabaseConnector.getMonthlyTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initializeTable(clientsFromDatabase);
        setUpButtons();
        displayMonthlyTotals(monthlyTotalsFromDatabase);
    } // initialize

    /**
     * Initializes the home page table, displaying all the current clients from the database.
     * @param clientsFromDatabase the list of clients from the SQL database
     */
    @FXML
    private void initializeTable(ObservableList<Client> clientsFromDatabase) {
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("dateJoined"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        tenantNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("tenantName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("propertyAddress"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("propertyRent"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("propertyExpenses"));
        commissionColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("commission"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Client, BigDecimal>("clientPayment"));
        table.setItems(clientsFromDatabase);
    } // initializeTable

    /**
     * Sets up the visibility of the add, del, and edit buttons.
     */
    @FXML
    private void setUpButtons() {
        addButton.setDisable(true);
        delButton.setDisable(true);
        editButton.setDisable(true);
        addButton.setVisible(false);
        delButton.setVisible(false);
        editButton.setVisible(false);
    } // setUpButtons

    /**
     * Displays the monthly totals.
     */
    @FXML
    private void displayMonthlyTotals(ObservableList<MonthlyTotals> monthlyTotalsFromDatabase) {
        Month month = LocalDate.now().getMonth();
        for (MonthlyTotals monthlyTotals : monthlyTotalsFromDatabase) {
            if (monthlyTotals.getCurrentMonth().equals(month.toString())) {
                MonthlyTotals currentMonthlyTotals = monthlyTotals;
                totalRentLabel.setText("$" + currentMonthlyTotals.getTotalMonthlyRent());
                totalExpensesLabel.setText("$" + currentMonthlyTotals.getTotalMonthlyExpenses());
                totalCommissionLabel.setText("$" + currentMonthlyTotals.getTotalMonthlyCommission());
                totalClientPaymentLabel.setText("$" + currentMonthlyTotals.getTotalMonthlyClientPayments());
            }
        }
    } // displayMonthlyTotals

    /**
     * Adds a client to the table.
     * TODO - Add "error check" that checks if the client's name is already in the table.
     */
    @FXML
    private void addClient() {
        Dialog<Client> addDialog = new AddDialog(new Client(), "Add New Client", "Add a new entry into the table.");
        ObservableList<Client> clientsFromDatabase;
        Optional<Client> result = addDialog.showAndWait();
        if (result.isPresent()) {
            Client newClient = result.get();
            int numOccur = checkHowManyTimesClientAppears(newClient);
            if (numOccur == 1) {
                newClient.setCommissionValue(new BigDecimal(0.09).setScale(2, RoundingMode.HALF_EVEN));
            } else if (numOccur >= 2) {
                newClient.setCommissionValue(new BigDecimal(0.08).setScale(2, RoundingMode.HALF_EVEN));
            } else {
                newClient.setCommissionValue(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_EVEN));
            }
            newClient.setCommission(newClient.getPropertyRent().multiply(newClient.getCommissionValue()));
            newClient.setClientPayment(newClient.getPropertyRent().subtract(newClient.getCommission()));
            try {
                DatabaseConnector.addClient(newClient);
                clientsFromDatabase = DatabaseConnector.getClients();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table.setItems(clientsFromDatabase);
        }
    } // addClient

    /**
     * Removes a client from the table.
     */
    @FXML
    private void removeClient() {
        // TODO - Add delay between displaying confirmation dialog and error dialog.
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        boolean ifOkayButtonPressed = displayConfirmationDialog();
        ObservableList<Client> clientsFromDatabase;
        if (selectedIndex == -1 && !ifOkayButtonPressed) {
            return;
        } else if (selectedIndex == -1 && ifOkayButtonPressed) {
            displayErrorDialog();
            return;
        } else {
            System.out.println(selectedIndex);
            try {
                DatabaseConnector.removeClient(selectedIndex);
                clientsFromDatabase = DatabaseConnector.getClients();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table.setItems(clientsFromDatabase);
        }
    } // removeClient

    /**
     * Checks how many times a client has appeared in the database.
     * @param aClient the client to be checked
     * @return - the number of times a client appears in the database.
     */
    @FXML
    private int checkHowManyTimesClientAppears(Client aClient) {
        int numOccur = 0;
        ObservableList<Client> clientsFromDatabase;
        try {
            clientsFromDatabase = DatabaseConnector.getClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Client client : clientsFromDatabase) {
            if (client.getClientName().equals(aClient.getClientName())) {
                numOccur++;
            }
        }
        return numOccur;
    } // checkHowManyTimesClientAppears

    /**
     * Edits a client in the table.
     */
    @FXML
    private void editClient() {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        ObservableList<Client> clientsFromDatabase;
        if (selectedIndex == -1) {
            displayErrorDialog();
            return;
        }
        try {
            clientsFromDatabase = DatabaseConnector.getClients();
            Dialog<Client> editDialog = new EditDialog(clientsFromDatabase.get(selectedIndex), "Edit Client", "Edit the current entry into the table.");
            Optional<Client> result = editDialog.showAndWait();
            if (result.isPresent()) {
                DatabaseConnector.updateClient(clientsFromDatabase.get(selectedIndex));
                // required to update the current list of clients from database as they have changed
                clientsFromDatabase = DatabaseConnector.getClients();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table.setItems(clientsFromDatabase); // will have updated client
    } // editClient

    /**
     * Displays a confirmation dialog.
     * @return - true if the ok button was pressed; false otherwise
     */
    @FXML
    private boolean displayConfirmationDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirm");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setGraphic(null);
        confirmationDialog.getDialogPane().getStylesheets().add(getClass().
                getResource(("del-dialog.css")).toExternalForm());
        confirmationDialog.setContentText("Delete this entry? Press OK to confirm.");
        Optional<ButtonType> answer = confirmationDialog.showAndWait();
        if ((answer.isPresent()) && (answer.get()) == ButtonType.OK) {
            confirmationDialog.close();
            return answer.isPresent();
        }
        return false;
    } // displayConfirmationDialog

    /**
     * Displays an error dialog.
     */
    private void displayErrorDialog() {
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Error");
        errorDialog.setHeaderText(null);
        errorDialog.setGraphic(null);
        errorDialog.getDialogPane().getStylesheets().add(getClass().
                getResource(("del-dialog.css")).toExternalForm());
        errorDialog.setContentText("No entry in the table was selected!");
        Optional<ButtonType> buttonPressed = errorDialog.showAndWait();
        if (buttonPressed.isPresent()) {
            errorDialog.close();
        }
    } // displayErrorDialog

    /**
     * Handles the toggling of the settings button when.
     */
    @FXML
    private void onToggleButtonClicked() {
        if (settingsButton.isSelected()) {
            addButton.setDisable(false);
            delButton.setDisable(false);
            editButton.setDisable(false);
            addButton.setVisible(true);
            delButton.setVisible(true);
            editButton.setVisible(true);
        } else {
            addButton.setDisable(true);
            delButton.setDisable(true);
            editButton.setDisable(true);
            addButton.setVisible(false);
            delButton.setVisible(false);
            editButton.setVisible(false);
        }
    }  // onToggleButtonClicked

} // class

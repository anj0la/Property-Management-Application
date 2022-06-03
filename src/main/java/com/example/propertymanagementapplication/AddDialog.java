package com.example.propertymanagementapplication;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddDialog extends Dialog<Client> {

    private Client newClient;
    private final Pane grid;
    private final Label dateJoined = new Label("Date Joined: ");
    private final Label newClientName = new Label("Client Name: ");
    private final Label newTenantName = new Label("Tenant Name: ");
    private final Label newAddress = new Label("Address: ");
    private final Label newRent = new Label("Rent: ");
    private final Label newExpenses = new Label("Expenses: ");
    private TextField dateInput;
    private TextField clientNameInput;
    private TextField tenantNameInput;
    private TextField addressInput;
    private TextField rentInput;
    private TextField expensesInput;

    public AddDialog(Client newClient) {
        super();
        this.setTitle("Add New Client");
        this.setContentText("Add a new entry into the table.");
        this.newClient = newClient;
        grid = createGrid();
        setUpAddDialog();
        receiveResults();
    } // Constructor

    // Methods
    private GridPane createGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(3);
        dateInput = new TextField();
        clientNameInput = new TextField();
        tenantNameInput = new TextField();
        addressInput = new TextField();
        rentInput = new TextField();
        expensesInput = new TextField();
        gridPane.add(dateJoined, 0, 1);
        gridPane.add(dateInput, 1, 1);
        gridPane.add(newClientName, 0, 2);
        gridPane.add(clientNameInput, 1, 2);
        gridPane.add(newTenantName, 0, 3);
        gridPane.add(tenantNameInput, 1, 3);
        gridPane.add(newAddress, 0, 4);
        gridPane.add(addressInput, 1, 4);
        gridPane.add(newRent, 0, 5);
        gridPane.add(rentInput, 1, 5);
        gridPane.add(newExpenses, 0, 6);
        gridPane.add(expensesInput, 1, 6);
        return gridPane;
    } // createGrid

    private void setUpAddDialog() {
        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().add(ButtonType.FINISH);
        Button finishButton = (Button) getDialogPane().lookupButton(ButtonType.FINISH);
        getDialogPane().getStylesheets().add(getClass().getResource(("add-dialog.css")).toExternalForm());
        finishButton.addEventFilter(ActionEvent.ACTION, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (invalidDialog()) {
                    event.consume();
                }
            }
        });
    }

    private boolean invalidDialog() {
        if (dateInput.getText().isEmpty() || clientNameInput.getText().isEmpty() || tenantNameInput.getText().isEmpty()
        || addressInput.getText().isEmpty() || rentInput.getText().isEmpty() || expensesInput.getText().isEmpty()) {
            return true;
        }
        /*if (new BigDecimal(rentInput.getText()) instanceof BigDecimal) {

        }*/
        return false;
    }

    public void receiveResults() {
        setResultConverter(new Callback<ButtonType, Client>() {
            @Override
            public Client call(ButtonType buttonType) {
                if (buttonType == ButtonType.FINISH) {
                    newClient.setDateJoined(dateInput.getText());
                    newClient.setClientName(clientNameInput.getText());
                    newClient.setTenantName(tenantNameInput.getText());
                    newClient.setPropertyAddress(addressInput.getText());
                    newClient.setPropertyRent(new BigDecimal(rentInput.getText()).
                            setScale(2, RoundingMode.HALF_EVEN));
                    newClient.setPropertyExpenses(new BigDecimal(expensesInput.getText()).
                            setScale(2, RoundingMode.HALF_EVEN));
                    return newClient;
                }
                return null;
            }
        });
    }
} // class

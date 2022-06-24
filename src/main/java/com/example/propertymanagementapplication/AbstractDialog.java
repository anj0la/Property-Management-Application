package com.example.propertymanagementapplication;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Creates an abstract dialog class, specifying how the dialog will open/close, and the value it will return.
 */
public abstract class AbstractDialog extends Dialog<Client> {

    protected Client client;
    protected final GridPane grid;
    protected final Label dateJoined = new Label("Date Joined: ");
    protected final Label clientName = new Label("Client Name: ");
    protected final Label tenantName = new Label("Tenant Name: ");
    protected final Label address = new Label("Address: ");
    protected final Label rent = new Label("Rent: ");
    protected final Label expenses = new Label("Expenses: ");
    protected TextField dateInput;
    protected TextField clientNameInput;
    protected TextField tenantNameInput;
    protected TextField addressInput;
    protected TextField rentInput;
    protected TextField expensesInput;

    /**
     * Construct an Abstract Dialog.
     * @param client the client
     * @param title the title of the dialog
     * @param content the content of the dialog
     */
    public AbstractDialog(Client client, String title, String content) {
        super();
        this.setTitle(title);
        this.setContentText(content);
        this.client = client;
        grid = createGrid();
        setUpDialog();
        receiveResults();
    } // Constructor

    /**
     * Creates the layout of the dialog.
     * @return - the layout of the dialog (a grid pane)
     */
    private GridPane createGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(3);
        dateInput = new TextField(client.getDateJoined());
        clientNameInput = new TextField(client.getClientName());
        tenantNameInput = new TextField(client.getTenantName());
        addressInput = new TextField(client.getPropertyAddress());
        rentInput = new TextField(client.getPropertyRent().toString());
        expensesInput = new TextField(client.getPropertyExpenses().toString());
        gridPane.add(dateJoined, 0, 1);
        gridPane.add(dateInput, 1, 1);
        gridPane.add(clientName, 0, 2);
        gridPane.add(clientNameInput, 1, 2);
        gridPane.add(tenantName, 0, 3);
        gridPane.add(tenantNameInput, 1, 3);
        gridPane.add(address, 0, 4);
        gridPane.add(addressInput, 1, 4);
        gridPane.add(rent, 0, 5);
        gridPane.add(rentInput, 1, 5);
        gridPane.add(expenses, 0, 6);
        gridPane.add(expensesInput, 1, 6);
        return gridPane;
    } // createGrid

    /**
     * Sets up the dialog.
     */
    private void setUpDialog() {
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
    } // setUpDialog

    /**
     * Checks if the dialog is invalid.
     * @return - true if the dialog has at least one entry that is empty; false otherwise.
     */
    private boolean invalidDialog() {
        if (dateInput.getText().isEmpty() || clientNameInput.getText().isEmpty() || tenantNameInput.getText().isEmpty()
                || addressInput.getText().isEmpty() || rentInput.getText().isEmpty() || expensesInput.getText().isEmpty()) {
            return true;
        }
        return false;
    } // invalidDialog

    /**
     * Receives the results of the dialog and will either return the client, or null if the finish button was not pressed.
     */
    public void receiveResults() {
        setResultConverter(new Callback<ButtonType, Client>() {
            @Override
            public Client call(ButtonType buttonType) {
                if (buttonType == ButtonType.FINISH) {
                    client.setDateJoined(dateInput.getText());
                    client.setClientName(clientNameInput.getText());
                    client.setTenantName(tenantNameInput.getText());
                    client.setPropertyAddress(addressInput.getText());
                    client.setPropertyRent(new BigDecimal(rentInput.getText()).
                            setScale(2, RoundingMode.HALF_EVEN));
                    client.setPropertyExpenses(new BigDecimal(expensesInput.getText()).
                            setScale(2, RoundingMode.HALF_EVEN));
                    return client;
                }
                return null;
            }
        });
    } // receiveResults

} // class

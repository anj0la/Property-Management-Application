package com.example.propertymanagementapplication;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    protected DatePicker dateInput;
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
        if (client.getDateJoined() != "") {
            LocalDate date = LocalDate.parse(client.getDateJoined());
            dateInput = new DatePicker(date);
        }
        else {
            dateInput = new DatePicker();
        }

        dateInput.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            { dateInput.setPromptText(pattern.toLowerCase()); }
            @Override public String toString(LocalDate date) {
                if (date != null) {
                    System.out.println(date);
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        clientNameInput = new TextField(client.getClientName());
        tenantNameInput = new TextField(client.getTenantName());
        addressInput = new TextField(client.getPropertyAddress());
        rentInput = new TextField(client.getPropertyRent().toString());
        expensesInput = new TextField(client.getPropertyExpenses().toString());
        createNumberFields();
        //setUpDateInput();
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

//    /**
//     * Sets the formatting of the date input so that the user adds the date in the correct format (e.g. 2022-08-05).
//     */
//    private void setUpDateInput() {
//        dateInput.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                String newValue) {
//                if (!newValue.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
//                    dateInput.setText(oldValue);
//                }
//            }
//        });
//    }


    /**
     * Changes the rent and expenses input into number fields that only allow decimal numbers.
     */
    private void createNumberFields() {
        rentInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    rentInput.setText(oldValue);
                }
            }
        });
        expensesInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    rentInput.setText(oldValue);
                }
            }
        });
    } // changeIntoNumberFields

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
        if (dateInput.getEditor().getText().isEmpty() || clientNameInput.getText().isEmpty() || tenantNameInput.getText().isEmpty()
                || addressInput.getText().isEmpty() || rentInput.getText().isEmpty() || expensesInput.getText().isEmpty()) {
            return true;
        }
        return false;
    } // invalidDialog

    /**
     * Receives the results of the dialog and will either return the client, or null if the finish button was not pressed.
     */
    private void receiveResults() {
        setResultConverter(new Callback<ButtonType, Client>() {
            @Override
            public Client call(ButtonType buttonType) {
                if (buttonType == ButtonType.FINISH) {
                    client.setDateJoined(dateInput.getEditor().getText());
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

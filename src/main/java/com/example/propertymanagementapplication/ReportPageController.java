package com.example.propertymanagementapplication;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class controls the report page. The methods presented below help with the functionality of the report page,
 * such as viewing the monthly and yearly table, and generating/sharing PDFs for both tables.
 */
public class ReportPageController implements Initializable {

    @FXML
    private Button shareButton;
    @FXML
    private ToggleButton settingsButton;
    @FXML
    private TableView<Client> table;
    @FXML
    private TableView<MonthlyTotals> yearlyTable;
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
    private TableColumn<MonthlyTotals, String> monthColumn;
    @FXML
    private TableColumn<MonthlyTotals, BigDecimal> totalRentColumn;
    @FXML
    private TableColumn<MonthlyTotals, BigDecimal> totalExpensesColumn;
    @FXML
    private TableColumn<MonthlyTotals, BigDecimal> totalCommissionColumn;
    @FXML
    private TableColumn<MonthlyTotals, BigDecimal> totalPaymentColumn;
    @FXML
    private Button monthlyReportButton;
    @FXML
    private Button yearlyReportButton;
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Client> clientsFromDatabase;
        ObservableList<MonthlyTotals> monthlyTotalsFromDatabase;
        try {
            clientsFromDatabase = DatabaseConnector.getClients();
            monthlyTotalsFromDatabase = DatabaseConnector.getMonthlyTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initializeTable(clientsFromDatabase);
        initializeYearlyTable(monthlyTotalsFromDatabase);
        setUpButtons();
        String userName = System.getProperty("user.name");
        if (System.getProperty("os.name").equals("Mac OS X")) {
            fileChooser.setInitialDirectory(new File("/Users/" + userName + "/Downloads"));
        }
        else {
            fileChooser.setInitialDirectory(new File("C\\" + userName + "\\Downloads"));
        }
    } // initialize

    /**
     * Initializes the report page monthly table, displaying all the current clients from the database.
     * @param clientsFromDatabase the list of clients from the SQL database
     */
    private void initializeTable(ObservableList<Client> clientsFromDatabase) {
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        tenantNameColumn.setCellValueFactory(new PropertyValueFactory<>("tenantName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("propertyAddress"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<>("propertyRent"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<>("propertyExpenses"));
        commissionColumn.setCellValueFactory(new PropertyValueFactory<>("commission"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("clientPayment"));
        table.setItems(clientsFromDatabase);
    } // initializeTable

    /**
     * Initializes the report page yearly table, displaying all the current clients from the database.
     * @param monthlyTotalsFromDatabase the list of monthly totals from the SQL database
     */
    private void initializeYearlyTable(ObservableList<MonthlyTotals> monthlyTotalsFromDatabase) {
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("currentMonth"));
        totalRentColumn.setCellValueFactory(new PropertyValueFactory<>("totalMonthlyRent"));
        totalExpensesColumn.setCellValueFactory(new PropertyValueFactory<>("totalMonthlyExpenses"));
        totalCommissionColumn.setCellValueFactory(new PropertyValueFactory<>("totalMonthlyCommission"));
        totalPaymentColumn.setCellValueFactory(new PropertyValueFactory<>("totalMonthlyClientPayments"));
        yearlyTable.setItems(monthlyTotalsFromDatabase);
        yearlyTable.setVisible(false);
    } // initializeYearlyTable

    /**
     * Sets up the visibility of the save and print buttons.
     */
    private void setUpButtons() {
        monthlyReportButton.setDisable(true);
        shareButton.setDisable(true);
        shareButton.setVisible(false);
    } // setUpButtons

    /**
     * Handles the toggling of the settings button. If selected, it displays the following share button, otherwise,
     * the share button is hidden.
     * @param event the toggle event
     */
    @FXML
    private void onToggleButtonClicked(ActionEvent event) {
        if (settingsButton.isSelected()) {
            shareButton.setDisable(false);
            shareButton.setVisible(true);
        } else {
            shareButton.setDisable(true);
            shareButton.setVisible(false);
        }
    }  // onToggleButtonClicked

    @FXML
    /**
     * Displays the save dialog where the user can select the directory to save their PDF to, and the name of
     * the PDF.
     */
    private void displaySaveDialog() throws IOException, SQLException {
        Stage stage = new Stage();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialFileName("generated-report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            saveReport(file);
        }
    } // displaySaveDialog

    /**
     * Saves the monthly report into a PDF file.
     * @param file the file to create the PDF
     * @throws IOException if the file cannot be created
     * @throws SQLException if the connection to the database does not work
     */
    @FXML
    private void saveReport(File file) throws IOException, SQLException {
        PdfCreator pdfCreator = new PdfCreator(file);
        if (monthlyReportButton.isDisabled()) {
            pdfCreator.createMonthlyReport();
        } else { // yearly report button has been selected
            pdfCreator.createYearlyReport();
        }
    } // saveReport

    /**
     * Displays the monthly table.
     */
    @FXML
    private void displayMonthlyTable() {
        yearlyReportButton.setDisable(false);
        yearlyTable.setVisible(false);
        table.setVisible(true);
        monthlyReportButton.setDisable(true);
    } // displayMonthlyTable

    /**
     * Displays the yearly table.
     */
    @FXML
    private void displayYearlyTable() {
        monthlyReportButton.setDisable(false);
        yearlyTable.setVisible(true);
        table.setVisible(false);
        yearlyReportButton.setDisable(true);
    } // displayMonthlyTable

} // class

package com.example.propertymanagementapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

public class DatabaseConnector {

    /**
     * Gets the MYSQL database connection.
     * @return - the connection to the database
     * @throws SQLException when no database connection can be established
     */
    public static Connection getDatabaseConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/anjola/Desktop/client.db");
        return connection;
    } // getDatabaseConnection

    /**
     * Gets and returns a list of clients in the database.
     * @return - an observable list of Clients
     * @throws SQLException when no database connection can be established
     */
    public static ObservableList<Client> getClients() throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<Client> listOfClients = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from regular_table");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            listOfClients.add(new Client(resultSet.getInt("client_id") ,resultSet.getString("date_joined"),
                    resultSet.getString("client_name"), resultSet.getString("tenant_name"),
                    resultSet.getString("address"), resultSet.getBigDecimal("rent"),
                    resultSet.getBigDecimal("expenses"), resultSet.getBigDecimal("commission"),
                    resultSet.getBigDecimal("client_payment")));
        }
        return listOfClients;
    } // getClients

    /**
     * Adds a client into the regular table in the database.
     * @param aClient the client to be added
     * @throws SQLException when no database connection can be established
     */
    public static void addClient(Client aClient) throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<Client> listOfClients = getClients();
        PreparedStatement pStatement = connection.prepareStatement("insert into regular_table (date_joined, client_name," +
                " tenant_name, address, rent, expenses, commission, client_payment)values(?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println(aClient.getDateJoined());
        pStatement.setString(1, aClient.getDateJoined());
        pStatement.setString(2, aClient.getClientName());
        pStatement.setString(3, aClient.getTenantName());
        pStatement.setString(4, aClient.getPropertyAddress());
        pStatement.setBigDecimal(5, aClient.getPropertyRent());
        pStatement.setBigDecimal(6, aClient.getPropertyExpenses());
        pStatement.setBigDecimal(7, aClient.getCommission());
        pStatement.setBigDecimal(8, aClient.getClientPayment());
        pStatement.execute();
    } // addClient

    /**
     * Removes a client from the regular table in the database.
     * @param selectedIndex the index of the client to be deleted
     * @throws SQLException when no database connection can be established
     */
    public static void removeClient(int selectedIndex) throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<Client> listOfClients = getClients();
        Client currentClient = listOfClients.get(selectedIndex);
        PreparedStatement pStatement = connection.prepareStatement("delete from regular_table where client_id = ?");
        pStatement.setInt(1, currentClient.getId());
        pStatement.execute();
    } // removeClient

    /**
     * Updates the selected client in the regular table.
     * @param selectedClient the client to have its values be updated in the table
     * @throws SQLException when no database connection can be established
     */
    public static void updateClient(Client selectedClient) throws SQLException {
        Connection connection = getDatabaseConnection();
        PreparedStatement pStatement = connection.prepareStatement("update regular_table set " +
                "date_joined = ?, client_name = ?, tenant_name = ?, address = ?, rent = ?, expenses = ?, " +
                "commission = ?, client_payment = ? where client_id = ?");
        pStatement.setString(1, selectedClient.getDateJoined());
        pStatement.setString(2, selectedClient.getClientName());
        pStatement.setString(3, selectedClient.getTenantName());
        pStatement.setString(4, selectedClient.getPropertyAddress());
        pStatement.setBigDecimal(5, selectedClient.getPropertyRent());
        pStatement.setBigDecimal(6, selectedClient.getPropertyExpenses());
        selectedClient.setCommission(selectedClient.getPropertyRent().multiply(selectedClient.getCommissionValue()));
        pStatement.setBigDecimal(7, selectedClient.getCommission());
        selectedClient.setClientPayment(selectedClient.getPropertyRent().subtract(selectedClient.getCommission()));
        pStatement.setBigDecimal(8, selectedClient.getClientPayment());
        pStatement.setInt(9, selectedClient.getId());
        int rows = pStatement.executeUpdate();
        System.out.println("Rows impacted: " + rows);
        connection.close();
    } // updateClient

    /**
     * Collects the monthly rent, expenses, commission and client payments.
     * @return - the monthly totals for the current month
     * @throws SQLException when no database connection can be established
     */
    public static MonthlyTotals collectMonthlyTotals(Month currentMonth) throws SQLException {
        MonthlyTotals currentMonthlyTotals = new MonthlyTotals();
        ObservableList<Client> listOfClients = getClients();
        for (Client client : listOfClients) {
            BigDecimal monthlyRent = currentMonthlyTotals.getTotalMonthlyRent();
            BigDecimal monthlyExpenses = currentMonthlyTotals.getTotalMonthlyExpenses();
            BigDecimal monthlyCommission = currentMonthlyTotals.getTotalMonthlyCommission();
            BigDecimal monthlyClientPayments = currentMonthlyTotals.getTotalMonthlyClientPayments();
            currentMonthlyTotals.setTotalMonthlyRent(monthlyRent.add(client.getPropertyRent()));
            currentMonthlyTotals.setTotalMonthlyExpenses(monthlyExpenses.add(client.getPropertyExpenses()));
            currentMonthlyTotals.setTotalMonthlyCommission(monthlyCommission.add(client.getCommission()));
            currentMonthlyTotals.setTotalMonthlyClientPayments(monthlyClientPayments.add(client.getClientPayment()));
        }
        currentMonthlyTotals.setCurrentMonth(currentMonth.toString());
        return currentMonthlyTotals;
    } // collectMonthlyTotals


    /**
     * Gets the monthly totals from the yearly table in the database.
     * @return - an observable list of monthly totals
     * @throws SQLException when no database connection can be established
     */
    public static ObservableList<MonthlyTotals> getMonthlyTotals() throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<MonthlyTotals> monthlyTotalsList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from yearly_table");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            monthlyTotalsList.add(new MonthlyTotals(resultSet.getString("month_id"),
                    resultSet.getBigDecimal("monthly_rent"),
                    resultSet.getBigDecimal("monthly_expenses"),
                    resultSet.getBigDecimal("monthly_commission"),
                    resultSet.getBigDecimal("monthly_client_payment")));
        }
        return monthlyTotalsList;
    } // getMonthlyTotals

    /**
     * Inserts monthly totals into the yearly table.
     * @throws SQLException when no database connection can be established
     */
    public static void insertMonthlyTotals(MonthlyTotals currentMonthlyTotals) throws SQLException {
        Connection connection = getDatabaseConnection();
        PreparedStatement pStatement = connection.prepareStatement("insert into yearly_table (month_id, monthly_rent," +
                " monthly_expenses, monthly_commission, monthly_client_payment)values(?, ?, ?, ?, ?)");
        pStatement.setString(1, currentMonthlyTotals.getCurrentMonth()); // CHANGE to the current month (multi threading stuff)
        pStatement.setBigDecimal(2, currentMonthlyTotals.getTotalMonthlyRent());
        pStatement.setBigDecimal(3, currentMonthlyTotals.getTotalMonthlyExpenses());
        pStatement.setBigDecimal(4, currentMonthlyTotals.getTotalMonthlyCommission());
        pStatement.setBigDecimal(5, currentMonthlyTotals.getTotalMonthlyClientPayments());
        pStatement.execute();
    } // insertMonthlyTotals

    /**
     * Updates the monthly totals.
     * @throws SQLException when no database connection can be established
     */
    public static void updateMonthlyTotals(MonthlyTotals currentMonthlyTotals) throws SQLException {
        Connection connection = getDatabaseConnection();
        PreparedStatement pStatement = connection.prepareStatement("update yearly_table set " +
                "monthly_rent = ?, monthly_expenses = ?, monthly_commission = ?, monthly_client_payment = ? where month_id = ?");
        pStatement.setBigDecimal(1, currentMonthlyTotals.getTotalMonthlyRent());
        pStatement.setBigDecimal(2, currentMonthlyTotals.getTotalMonthlyExpenses());
        pStatement.setBigDecimal(3, currentMonthlyTotals.getTotalMonthlyCommission());
        pStatement.setBigDecimal(4, currentMonthlyTotals.getTotalMonthlyClientPayments());
        pStatement.setString(5, currentMonthlyTotals.getCurrentMonth());
        int rows = pStatement.executeUpdate();
        System.out.println("Rows impacted: " + rows);
        connection.close();
    } // updateMonthlyTotals

    /**
     * Handles and executes the correct method to deal with the monthly total values in the database.
     * @throws SQLException when no database connection can be established
     */
    public static void handleMonthlyTotals () throws SQLException {
        Month currentMonth = LocalDate.now().getMonth();
        ObservableList<MonthlyTotals> monthlyTotalsFromDatabase = getMonthlyTotals();
        MonthlyTotals currMonthlyTotals = collectMonthlyTotals(currentMonth);
        if (!isMonthInDatabase(currentMonth, monthlyTotalsFromDatabase)) {
            insertMonthlyTotals(currMonthlyTotals);
        } else {
            updateMonthlyTotals(currMonthlyTotals);
        }
    }  // handleMonthlyTotals

    /**
     * Checks if the current month is in the yearly table database.
     * @returns - true if the current month is in the database; false otherwise
     */
    private static boolean isMonthInDatabase(Month currentMonth, ObservableList<MonthlyTotals> monthlyTotalsFromDatabase) {
        for (MonthlyTotals monthlyTotals : monthlyTotalsFromDatabase) {
            if (monthlyTotals.getCurrentMonth().equals(currentMonth.toString())) {
                return true;
            }
        }
        return false;
    } // isMonthInDatabase

} // class

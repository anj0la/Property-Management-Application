package com.example.propertymanagementapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

public class DatabaseConnector {

    public static Connection getDatabaseConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/client", "root", "");
        return connection;
    } // getDatabaseConnection

    public static ObservableList<Client> getClients() throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<Client> listOfClients = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from client.regular_table");
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

    public static void addClient(Client aClient) throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<Client> listOfClients = getClients();
        PreparedStatement pStatement = connection.prepareStatement("insert into regular_table (date_joined, client_name," +
                " tenant_name, address, rent, expenses, commission, client_payment)values(?, ?, ?, ?, ?, ?, ?, ?)");
        pStatement.setString(1, aClient.getDateJoined());
        pStatement.setString(2, aClient.getClientName());
        pStatement.setString(3, aClient.getTenantName());
        pStatement.setString(4, aClient.getPropertyAddress());
        pStatement.setBigDecimal(5, aClient.getPropertyRent());
        pStatement.setBigDecimal(6, aClient.getPropertyExpenses());
        pStatement.setBigDecimal(7, aClient.getCommission());
        pStatement.setBigDecimal(8, aClient.getPaymentToClient());
        pStatement.execute();
    } // addClient

    public static void removeClient(int selectedIndex) throws SQLException {
        Connection connection = getDatabaseConnection();
        ObservableList<Client> listOfClients = getClients();
        Client currentClient = listOfClients.get(selectedIndex);
        PreparedStatement pStatement = connection.prepareStatement("delete from client.regular_table where client_id = ?");
        // PreparedStatement pStatement = connection.prepareStatement("delete from client.regular_table where client_id = client.getID");
        pStatement.setInt(1, currentClient.getId());
        pStatement.execute();
    } // removeClient


    public static void updateClient(Client selectedClient) throws SQLException {
        Connection connection = getDatabaseConnection();
        PreparedStatement pStatement = connection.prepareStatement("update client.regular_table set " +
                "date_joined = ?, client_name = ?, tenant_name = ?, address = ?, rent = ?, expenses = ?, " +
                "commission = ?, client_payment = ? where client_id = ?");
        pStatement.setString(1, selectedClient.getDateJoined());
        pStatement.setString(2, selectedClient.getClientName());
        pStatement.setString(3, selectedClient.getTenantName());
        pStatement.setString(4, selectedClient.getPropertyAddress());
        pStatement.setBigDecimal(5, selectedClient.getPropertyRent());
        pStatement.setBigDecimal(6, selectedClient.getPropertyExpenses());
        pStatement.setBigDecimal(7, selectedClient.getCommission());
        selectedClient.setPaymentToClient(selectedClient.getPropertyRent().subtract(selectedClient.getCommission()));
        pStatement.setBigDecimal(8, selectedClient.getPaymentToClient());
        pStatement.setInt(9, selectedClient.getId());
        int rows = pStatement.executeUpdate();
        System.out.println("Rows impacted: " + rows);
        connection.close();
    }

} // class

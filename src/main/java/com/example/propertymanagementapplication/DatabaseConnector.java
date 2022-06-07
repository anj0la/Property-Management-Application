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
            listOfClients.add(new Client(resultSet.getString("date_joined"),
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
        PreparedStatement pStatement = connection.prepareStatement("insert into regular_table (client_id, date_joined, client_name," +
                " tenant_name, address, rent, expenses, commission, client_payment)values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pStatement.setInt(1, aClient.getClientID());
        pStatement.setString(2, aClient.getDateJoined());
        pStatement.setString(3, aClient.getClientName());
        pStatement.setString(4, aClient.getTenantName());
        pStatement.setString(5, aClient.getPropertyAddress());
        pStatement.setBigDecimal(6, aClient.getPropertyRent());
        pStatement.setBigDecimal(7, aClient.getPropertyExpenses());
        pStatement.setBigDecimal(8, aClient.getCommission());
        pStatement.setBigDecimal(9, aClient.getPaymentToClient());
        pStatement.execute();
    }

} // class

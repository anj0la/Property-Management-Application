package com.example.propertymanagementapplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Client {

    private static int clientIDTracker = 0;
    private int clientID;
    private String dateJoined;
    private String clientName;
    private String tenantName;
    private String propertyAddress;
    private BigDecimal propertyRent;
    private BigDecimal propertyExpenses;
    private BigDecimal commission;
    private BigDecimal paymentToClient;

    public Client(String dateJoined, String clientName, String tenantName, String propertyAddress, BigDecimal rent,
                  BigDecimal expenses, BigDecimal commission, BigDecimal paymentToClient) {
        clientID = setClientID();
        this.dateJoined = dateJoined;
        this.clientName = clientName;
        this.tenantName = tenantName;
        this.propertyAddress = propertyAddress;
        this.propertyRent = rent;
        this.propertyExpenses = expenses;
        this.commission = commission;
        this.paymentToClient = paymentToClient;
    } // Constructor

    public Client() {
        this("", "", "", "",
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN));
    }

    public int getClientID() {
        return clientID;
    }

    public int setClientID() {
        clientID = clientIDTracker + 1;
        clientIDTracker = clientID;
        return clientIDTracker;
    }
    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public BigDecimal getPropertyRent() {
        return propertyRent;
    }

    public void setPropertyRent(BigDecimal propertyRent) {
        this.propertyRent = propertyRent;
    }

    public BigDecimal getPropertyExpenses() {
        return propertyExpenses;
    }

    public void setPropertyExpenses(BigDecimal propertyExpenses) {
        this.propertyExpenses = propertyExpenses;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getPaymentToClient() {
        return paymentToClient;
    }

    public void setPaymentToClient(BigDecimal paymentToClient) {
        this.paymentToClient = paymentToClient;
    }
} // class

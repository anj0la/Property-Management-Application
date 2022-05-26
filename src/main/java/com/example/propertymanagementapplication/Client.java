package com.example.propertymanagementapplication;

import java.util.Date;

public class Client {

    private Date currentDate;
    private String clientName;
    private String tenantName;
    private String propertyAddress;
    private double propertyRent;
    private double propertyExpenses;
    private double commission;
    private double paymentToClient;

    public Client(Date currentDate, String clientName, String tenantName, String propertyAddress, double rent,
                  double expenses, double commission, double paymentToClient) {
        this.currentDate = currentDate;
        this.clientName = clientName;
        this.tenantName = tenantName;
        this.propertyAddress = propertyAddress;
        this.propertyRent = rent;
        this.propertyExpenses = expenses;
        this.commission = commission;
        this.paymentToClient = paymentToClient;
    } // Constructor


    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
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

    public double getPropertyRent() {
        return propertyRent;
    }

    public void setPropertyRent(double propertyRent) {
        this.propertyRent = propertyRent;
    }

    public double getPropertyExpenses() {
        return propertyExpenses;
    }

    public void setPropertyExpenses(double propertyExpenses) {
        this.propertyExpenses = propertyExpenses;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getPaymentToClient() {
        return paymentToClient;
    }

    public void setPaymentToClient(double paymentToClient) {
        this.paymentToClient = paymentToClient;
    }

} // class

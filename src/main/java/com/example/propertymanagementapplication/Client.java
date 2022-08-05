package com.example.propertymanagementapplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Client {

    private final int id;
    private String dateJoined;
    private String clientName;
    private String tenantName;
    private String propertyAddress;
    private BigDecimal propertyRent;
    private BigDecimal propertyExpenses;
    private BigDecimal commission;
    private BigDecimal clientPayment;
    private BigDecimal commissionValue;

    /**
     * Creates a client with the given parameters.
     * @param id the ID of the client
     * @param dateJoined the date when the client joined the company
     * @param clientName the name of the client
     * @param tenantName the name of the tenant living in client's property
     * @param propertyAddress the address of the client's property
     * @param rent the rent that the tenant pays each month to live on the property
     * @param expenses the expenses on the property
     * @param commission the commission that the company gets
     * @param clientPayment the payment that the client receives
     */
    public Client(int id, String dateJoined, String clientName, String tenantName, String propertyAddress, BigDecimal rent,
                  BigDecimal expenses, BigDecimal commission, BigDecimal clientPayment) {
        this.id = id;
        this.dateJoined = dateJoined;
        this.clientName = clientName;
        this.tenantName = tenantName;
        this.propertyAddress = propertyAddress;
        this.propertyRent = rent;
        this.propertyExpenses = expenses;
        this.commission = commission;
        this.clientPayment = clientPayment;
        commissionValue = new BigDecimal("0.10").setScale(2, RoundingMode.HALF_EVEN);
    } // Constructor

    /**
     * The default constructor, which creates a new Client with default fields.
     */
    public Client() {
        this(Integer.MAX_VALUE, "", "", "", "",
                new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN));
    } // Default constructor

    /**
     * Gets the id of the client.
     * @return - id of the client
     */
    public int getId() {
        return id;
    } // getId

    /**
     * Gets the date the client joined.
     * @return - the date the client joined
     */
    public String getDateJoined() {
        return dateJoined;
    } // getDateJoined

    /**
     * Sets the client's join date.
     * @param dateJoined the date the client joined
     */
    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    } // setDateJoined

    /**
     * Gets the client's name.
     * @return - the name of the client
     */
    public String getClientName() {
        return clientName;
    } // getClientName

    /**
     * Sets the client's name.
     * @param clientName the name to set the client to
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    } // setClientName

    /**
     * Gets the tenant's name.
     * @return - the name of the tenant
     */
    public String getTenantName() {
        return tenantName;
    } // getTenantName

    /**
     * Sets the tenant's name.
     * @param tenantName the name to set the tenant to
     */
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    } // setTenantName

    /**
     * Gets the address of the client's property.
     * @return - the property address
     */
    public String getPropertyAddress() {
        return propertyAddress;
    } // getPropertyAddress

    /**
     * Sets the address name of the client's property.
     * @param propertyAddress the new address name
     */
    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    } // setPropertyAddress

    /**
     * Gets the rent for the client's property.
     * @return - the rent of the property
     */
    public BigDecimal getPropertyRent() {
        return propertyRent;
    } // getPropertyRent

    /**
     * Sets the rent for the client's property.
     * @param propertyRent the new rent value
     */
    public void setPropertyRent(BigDecimal propertyRent) {
        this.propertyRent = propertyRent;
    } // setPropertyRent

    /**
     * Gets the property's expenses.
     * @return - the expenses value
     */
    public BigDecimal getPropertyExpenses() {
        return propertyExpenses;
    } // getPropertyExpenses

    /**
     * Sets the property's expenses.
     * @param propertyExpenses the property expenses value
     */
    public void setPropertyExpenses(BigDecimal propertyExpenses) {
        this.propertyExpenses = propertyExpenses;
    } // setPropertyExpenses

    /**
     * Gets the commission.
     * @return - the commission value
     */
    public BigDecimal getCommission() {
        return commission;
    } // getCommission

    /**
     * Sets the commission value.
     * @param commission the new commission value
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    } // setCommission

    /**
     * Gets the payment of the client.
     * @return - the client's payment
     */
    public BigDecimal getClientPayment() {
        return clientPayment;
    } // getPaymentToClient

    /**
     * Sets the payment to the client.
     * @param clientPayment the value to be paid to the client
     */
    public void setClientPayment(BigDecimal clientPayment) {
        this.clientPayment = clientPayment;
    } // setPaymentToClient

    /**
     * Gets the current commission value.
     * @return - the commission value
     */
    public BigDecimal getCommissionValue() {
        return commissionValue;
    } // getCommissionValue

    /**
     * Changes the commission value.
     * @param commissionValue - the new value to be set as the commission value
     */
    public void setCommissionValue(BigDecimal commissionValue) {
        this.commissionValue = commissionValue;
    } // setCommissionValue

} // class

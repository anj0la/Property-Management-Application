package com.example.propertymanagementapplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MonthlyTotals {

    private String currentMonth;
    private BigDecimal totalMonthlyRent;
    private BigDecimal totalMonthlyExpenses;
    private BigDecimal totalMonthlyCommission;
    private BigDecimal totalMonthlyClientPayments;

    public MonthlyTotals(String currentMonth, BigDecimal totalMonthlyRent, BigDecimal totalMonthlyExpenses,
                         BigDecimal totalMonthlyCommission, BigDecimal totalMonthlyClientPayments) {
        this.currentMonth = currentMonth;
        this.totalMonthlyRent = totalMonthlyRent;
        this.totalMonthlyExpenses = totalMonthlyExpenses;
        this.totalMonthlyCommission = totalMonthlyCommission;
        this.totalMonthlyClientPayments = totalMonthlyClientPayments;
    }

    public MonthlyTotals() {
        this("", new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN));
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public BigDecimal getTotalMonthlyRent() {
        return totalMonthlyRent;
    }

    public void setTotalMonthlyRent(BigDecimal totalMonthlyRent) {
        this.totalMonthlyRent = totalMonthlyRent;
    }

    public BigDecimal getTotalMonthlyExpenses() {
        return totalMonthlyExpenses;
    }

    public void setTotalMonthlyExpenses(BigDecimal totalMonthlyExpenses) {
        this.totalMonthlyExpenses = totalMonthlyExpenses;
    }

    public BigDecimal getTotalMonthlyCommission() {
        return totalMonthlyCommission;
    }

    public void setTotalMonthlyCommission(BigDecimal totalMonthlyCommission) {
        this.totalMonthlyCommission = totalMonthlyCommission;
    }

    public BigDecimal getTotalMonthlyClientPayments() {
        return totalMonthlyClientPayments;
    }

    public void setTotalMonthlyClientPayments(BigDecimal totalMonthlyClientPayments) {
        this.totalMonthlyClientPayments = totalMonthlyClientPayments;
    }

} // class


package com.bank.model;

import java.sql.Timestamp;

public class Transaction {

    private String type;
    private double amount;
    private Long receiverAccount;
    private Timestamp transactionDate;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Long getReceiverAccount() { return receiverAccount; }
    public void setReceiverAccount(Long receiverAccount) { this.receiverAccount = receiverAccount; }

    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}
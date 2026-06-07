package com.bank.model;

public class User {

    private int id;
    private long accountNumber;
    private String username;
    private String password;
    private double balance;
    private String role;

    // ✅ ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ✅ Account Number
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    // ✅ Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ✅ Password (what you asked)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ Balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // ✅ Role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
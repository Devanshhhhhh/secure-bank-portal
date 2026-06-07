package com.bank.dao;

import java.sql.*;
import java.util.*;
import com.bank.model.User;

public class UserDAO {

    // Generate unique account number
    public static long generateAccountNumber() throws Exception {
        Connection con = DBConnection.getConnection();
        long account;
        while (true) {
            account = 1000000000L + (long)(Math.random() * 9000000000L);
            PreparedStatement ps = con.prepareStatement(
                "SELECT account_number FROM users WHERE account_number=?"
            );
            ps.setLong(1, account);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) break;
        }
        con.close();
        return account;
    }

    // Register
    public static boolean register(User user) throws Exception {

    Connection con = DBConnection.getConnection();

    PreparedStatement ps = con.prepareStatement(
        "INSERT INTO users(account_number, username, password, balance, role) VALUES(?,?,?,?,?)"
    );

    ps.setLong(1, user.getAccountNumber());
    ps.setString(2, user.getUsername());
    ps.setString(3, user.getPassword());
    ps.setDouble(4, 0.0); // ✅ Always 0
    ps.setString(5, "USER");

    int i = ps.executeUpdate();
    con.close();

    return i > 0;
    }

    // Login
    public static User login(String username, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM users WHERE username=? AND password=?"
        );
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setAccountNumber(rs.getLong("account_number"));
            user.setUsername(rs.getString("username"));
            user.setBalance(rs.getDouble("balance"));
            user.setRole(rs.getString("role"));
        }
        con.close();
        return user;
    }

    // Deposit
    public static void deposit(long acc, double amt) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement(
            "UPDATE users SET balance = balance + ? WHERE account_number=?"
        );
        ps.setDouble(1, amt);
        ps.setLong(2, acc);
        ps.executeUpdate();

        PreparedStatement tr = con.prepareStatement(
            "INSERT INTO transactions(account_number,type,amount) VALUES(?,?,?)"
        );
        tr.setLong(1, acc);
        tr.setString(2, "DEPOSIT");
        tr.setDouble(3, amt);
        tr.executeUpdate();

        con.commit();
        con.close();
    }

    // Withdraw
    public static void withdraw(long acc, double amt) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement(
            "UPDATE users SET balance = balance - ? WHERE account_number=?"
        );
        ps.setDouble(1, amt);
        ps.setLong(2, acc);
        ps.executeUpdate();

        PreparedStatement tr = con.prepareStatement(
            "INSERT INTO transactions(account_number,type,amount) VALUES(?,?,?)"
        );
        tr.setLong(1, acc);
        tr.setString(2, "WITHDRAW");
        tr.setDouble(3, amt);
        tr.executeUpdate();

        con.commit();
        con.close();
    }

    // Transfer
    public static boolean transfer(long senderAcc,
                               long receiverAcc,
                               double amount) throws Exception {

    Connection con = DBConnection.getConnection();
    con.setAutoCommit(false); // transaction start

    try {

        // 1️⃣ Check sender balance
        PreparedStatement check = con.prepareStatement(
            "SELECT balance FROM users WHERE account_number=?"
        );
        check.setLong(1, senderAcc);
        ResultSet rs = check.executeQuery();

        if (!rs.next()) {
            return false;
        }

        double balance = rs.getDouble("balance");

        if (balance < amount) {
            return false; // insufficient balance
        }

        // 2️⃣ Deduct from sender
        PreparedStatement deduct = con.prepareStatement(
            "UPDATE users SET balance=balance-? WHERE account_number=?"
        );
        deduct.setDouble(1, amount);
        deduct.setLong(2, senderAcc);
        deduct.executeUpdate();

        // 3️⃣ Add to receiver
        PreparedStatement add = con.prepareStatement(
            "UPDATE users SET balance=balance+? WHERE account_number=?"
        );
        add.setDouble(1, amount);
        add.setLong(2, receiverAcc);
        add.executeUpdate();

        // 4️⃣ Insert transaction record
        PreparedStatement insert = con.prepareStatement(
            "INSERT INTO transactions(account_number,type,amount,receiver_account) VALUES(?,?,?,?)"
        );
        insert.setLong(1, senderAcc);
        insert.setString(2, "TRANSFER");
        insert.setDouble(3, amount);
        insert.setLong(4, receiverAcc);
        insert.executeUpdate();

        con.commit();
        con.close();

        return true;

    } catch (Exception e) {
        con.rollback();
        con.close();
        e.printStackTrace();
        return false;
    }
    }

    // Get All Users (Admin)
    public static List<User> getAllUsers() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");

        List<User> list = new ArrayList<>();
        while(rs.next()){
            User u = new User();
            u.setAccountNumber(rs.getLong("account_number"));
            u.setUsername(rs.getString("username"));
            u.setBalance(rs.getDouble("balance"));
            u.setRole(rs.getString("role"));
            list.add(u);
        }
        con.close();
        return list;
    }

    // Delete
    public static void deleteUser(long acc) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM users WHERE account_number=?"
        );
        ps.setLong(1, acc);
        ps.executeUpdate();
        con.close();
    }

    public static User getUserByAccount(long accountNumber) throws Exception {

    Connection con = DBConnection.getConnection();

    PreparedStatement ps = con.prepareStatement(
        "SELECT * FROM users WHERE account_number=?"
    );
    ps.setLong(1, accountNumber);

    ResultSet rs = ps.executeQuery();

    User user = null;

    if (rs.next()) {
        user = new User();
        user.setAccountNumber(rs.getLong("account_number"));
        user.setUsername(rs.getString("username"));
        user.setBalance(rs.getDouble("balance"));
        user.setRole(rs.getString("role"));
    }

    con.close();
    return user;
}
}
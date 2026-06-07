package com.bank.dao;

import java.sql.*;
import java.util.*;
import com.bank.model.Transaction;

public class TransactionDAO {

    public static List<Transaction> getTransactions(long accountNumber) throws Exception {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM transactions WHERE account_number=? ORDER BY transaction_date DESC"
        );

        ps.setLong(1, accountNumber);

        ResultSet rs = ps.executeQuery();

        List<Transaction> list = new ArrayList<>();

        while (rs.next()) {
            Transaction t = new Transaction();
            t.setType(rs.getString("type"));
            t.setAmount(rs.getDouble("amount"));
            t.setReceiverAccount((Long) rs.getObject("receiver_account"));
            t.setTransactionDate(rs.getTimestamp("transaction_date"));
            list.add(t);
        }

        con.close();
        return list;
    }
}
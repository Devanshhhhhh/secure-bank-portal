package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;
import com.bank.model.User;

public class TransactionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        try {
            List<Transaction> transactions =
                    TransactionDAO.getTransactions(user.getAccountNumber());

            request.setAttribute("transactions", transactions);

            RequestDispatcher rd =
                    request.getRequestDispatcher("transactions.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
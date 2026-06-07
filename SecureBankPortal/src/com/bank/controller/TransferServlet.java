package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.bank.dao.UserDAO;
import com.bank.model.User;

public class TransferServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Create session object
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        long receiver = Long.parseLong(request.getParameter("receiverAccount"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            boolean status = UserDAO.transfer(
                    user.getAccountNumber(),
                    receiver,
                    amount
            );

            if (status) {
                // reload updated user balance
                User updatedUser = UserDAO.getUserByAccount(user.getAccountNumber());
                session.setAttribute("user", updatedUser);

                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("transfer.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
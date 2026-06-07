package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.bank.dao.UserDAO;
import com.bank.model.User;

public class DepositServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            // 1️⃣ Update DB
            UserDAO.deposit(user.getAccountNumber(), amount);

            // 2️⃣ Reload Updated User
            User updatedUser = UserDAO.getUserByAccount(user.getAccountNumber());

            // 3️⃣ Update Session
            session.setAttribute("user", updatedUser);

            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
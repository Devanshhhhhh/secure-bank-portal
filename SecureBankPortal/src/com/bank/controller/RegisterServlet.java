package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.bank.dao.UserDAO;
import com.bank.model.User;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {

                long accountNumber = UserDAO.generateAccountNumber();

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setAccountNumber(accountNumber);
                user.setBalance(0.0);     // ✅ Default balance = 0
                user.setRole("USER");     // ✅ Default role

                boolean status = UserDAO.register(user);

                if (status) {
                    response.sendRedirect("login.jsp?success=1");
                } else {
                    response.sendRedirect("register.jsp?error=1");
                }

            } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
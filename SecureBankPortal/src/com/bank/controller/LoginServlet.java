package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.bank.dao.UserDAO;
import com.bank.model.User;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = UserDAO.login(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                    response.sendRedirect("AdminServlet");
                } else {
                    response.sendRedirect("dashboard.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid Credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
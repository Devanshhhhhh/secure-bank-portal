package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.bank.dao.UserDAO;
import com.bank.model.User;

public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<User> users = UserDAO.getAllUsers();
            request.setAttribute("users", users);

            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
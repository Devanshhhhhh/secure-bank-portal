package com.bank.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.bank.dao.UserDAO;

public class DeleteAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        long account = Long.parseLong(request.getParameter("account"));

        try {
            UserDAO.deleteUser(account);
            response.sendRedirect("AdminServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
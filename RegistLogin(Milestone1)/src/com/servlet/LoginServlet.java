package com.servlet;

import com.dao.UserDAO;
import com.model.User;
import com.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet("/LoginServlet")
//package com.servlet;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import com.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Authenticate user
        boolean isAuthenticated = UserDAO.authenticateUser(email, password);
        if (!isAuthenticated) {
            request.setAttribute("error", "Invalid login credentials.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        // Start session and redirect
        HttpSession session = request.getSession();
        session.setAttribute("user", email);
        response.sendRedirect("Dashboard.jsp");
    }
}

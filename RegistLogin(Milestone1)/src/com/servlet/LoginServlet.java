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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Fetch user from DAO
        User user = UserDAO.getUserByEmail(email);

        if (user == null || !PasswordUtil.checkPassword(password, user.getPassword())) {
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Login successful – create session
        HttpSession session = request.getSession();
        session.setAttribute("user", user); // store full User object

        // Redirect to dashboard
        response.sendRedirect("dashboard.jsp");
    }
}

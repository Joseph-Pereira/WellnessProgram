package com.servlet;

import com.dao.UserDAO;
import com.model.User;
import com.util.PasswordUtil;
import com.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        List<String> errors = new ArrayList<>();

        // Validate email
        if (email == null || !ValidationUtil.isValidEmail(email)) {
            errors.add("Invalid email format.");
        }

        // Validate password
        if (password == null || !PasswordUtil.isValidPassword(password)) {
            errors.add("Password must be at least 8 characters, include 1 uppercase letter, 1 digit, and 1 special character.");
        }

        //  Validate phone number
        if (phone == null || !ValidationUtil.isValidPhoneNumber(phone)) {
            errors.add("Phone number must be exactly 10 digits and start with a 0.");
        }

        // Check for duplicate email
        if (UserDAO.emailExists(email)) {
            errors.add("An account with this email already exists.");
        }

        // If errors, return to register.jsp
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Hash password and create user
        String hashedPassword = PasswordUtil.hashPassword(password);
        User newUser = new User(email, hashedPassword, phone);
        boolean success = UserDAO.registerUser(newUser);

        if (!success) {
            errors.add("Something went wrong during registration.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        //  Start session and redirect to dashboard
        HttpSession session = request.getSession();
        session.setAttribute("user", newUser.getEmail());
        response.sendRedirect("dashboard.jsp");
    }
}

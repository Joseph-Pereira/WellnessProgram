package com.servlet;

import com.dao.UserDAO;
import com.model.User;
import com.util.PasswordUtil;
import com.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        List<String> errors = new ArrayList<>();

        // Validate email
        if (!ValidationUtil.isValidEmail(email)) {
            errors.add("Invalid email format.");
        }

        // Validate password
        if (!PasswordUtil.isValidPassword(password)) {
            errors.add("Password must be at least 8 characters, include 1 uppercase letter, 1 digit, and 1 special character.");
        }

        // Validate phone
        if (!ValidationUtil.isValidPhoneNumber(phone)) {
            errors.add("Phone number must be exactly 10 digits and start with a 0.");
        }

        // Check for duplicate email
        if (UserDAO.emailExists(email)) {
            errors.add("An account with this email already exists.");
        }

        // If any errors, return to form
        if (!errors.isEmpty()) {
            request.setAttribute("errorMessage", errors.get(0)); // Show only first error
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Hash password and create user object
        String hashedPassword = PasswordUtil.hashPassword(password);
        User newUser = new User(email, hashedPassword, phone);

        boolean success = UserDAO.registerUser(newUser);

        if (!success) {
            request.setAttribute("errorMessage", "Something went wrong during registration.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // On success: set success message or redirect to login
        request.setAttribute("successMessage", "Registration successful! You may now log in.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}

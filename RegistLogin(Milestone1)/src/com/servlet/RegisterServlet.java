@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        List<String> errors = new ArrayList<>();

        // Input Validation
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Invalid email format.");
        }

        if (password == null || !password.matches(PASSWORD_REGEX)) {
            errors.add("Password must be at least 8 characters, include 1 uppercase letter, 1 digit, and 1 special character.");
        }

        if (phone == null || !phone.matches("^[0-9]{10,15}$")) {
            errors.add("Invalid phone number. Digits only.");
        }

        // Duplicate check (pseudo-code - replace with actual DB logic)
        boolean emailExists = UserDAO.emailExists(email); // Assume you have a UserDAO class
        if (emailExists) {
            errors.add("Email is already registered.");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Register user
        boolean success = UserDAO.registerUser(email, password, phone); // Hash password in real apps
        if (!success) {
            errors.add("Internal error during registration. Please try again.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Redirect to dashboard
        HttpSession session = request.getSession();
        session.setAttribute("user", email);
        response.sendRedirect("dashboard.jsp");
    }
}

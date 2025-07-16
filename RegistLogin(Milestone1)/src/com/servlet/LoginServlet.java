@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        // Authenticate user
        boolean isAuthenticated = UserDAO.authenticateUser(email, password, phone);
        if (!isAuthenticated) {
            request.setAttribute("error", "Invalid login credentials.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        // Start session and redirect
        HttpSession session = request.getSession();
        session.setAttribute("user", email);
        response.sendRedirect("dashboard.jsp");
    }
}

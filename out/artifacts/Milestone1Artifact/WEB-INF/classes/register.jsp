<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow-lg mx-auto" style="max-width: 500px;">
        <div class="card-body">
            <h3 class="card-title text-center">Student Registration</h3>
            <form action="register" method="post">
                <div class="mb-3">
                    <label class="form-label">Student Number</label>
                    <input type="text" name="student_number" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Surname</label>
                    <input type="text" name="surname" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Phone</label>
                    <input type="text" name="phone" class="form-control" pattern="\d{10}" required aria-describedby="phoneHelp">
                    <div id="phoneHelp" class="form-text">Please enter a 10-digit phone number (e.g., 0123456789). No spaces or dashes.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Register</button>
            </form>

            <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger mt-3"><%= request.getAttribute("errorMessage") %></div>
            <% } else if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success mt-3"><%= request.getAttribute("successMessage") %></div>
            <% } %>

            <div class="text-center mt-3">
                <a href="login.jsp" class="text-muted">Already have an account? Login</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>

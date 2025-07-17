<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow-lg mx-auto" style="max-width: 400px;">
        <div class="card-body">
            <h3 class="card-title text-center">Student Login</h3>
            <form action="login" method="post">
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>

            <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger mt-3"><%= request.getAttribute("errorMessage") %></div>
            <% } %>

            <div class="text-center mt-3">
                <a href="register.jsp" class="text-muted">Don't have an account? Register</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>

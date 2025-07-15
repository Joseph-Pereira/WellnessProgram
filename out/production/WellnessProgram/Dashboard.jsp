<%@ page language="java" contentType="text/html; charset=UTF-8" session="true" %>
<%
    String studentName = (String) session.getAttribute("studentName");
    if (studentName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5 text-center">
    <div class="card shadow-lg mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h2>Welcome, <%= studentName %>!</h2>
            <p class="lead">You are now logged in to the Student Wellness System.</p>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="btn btn-danger mt-3">Logout</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

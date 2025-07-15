<%@ page language="java" contentType="text/html; charset=UTF-8" session="true" %>
<%
    String studentName = (String) session.getAttribute("studentName");
    if (studentName == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= studentName %>!</h2>
    <p>You are now logged in to the Student Wellness System.</p>

    <form action="LogoutServlet" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>

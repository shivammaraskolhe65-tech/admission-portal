<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    if(session.getAttribute("student_id") == null){
        response.sendRedirect("index.html");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <style>
        body { margin: 0; font-family: Arial, sans-serif; background-color: #F8FAFC; }
        .header { background-color: #1E40AF; color: white; padding: 15px; text-align: center; }
        .sidebar { width: 220px; height: 100vh; background-color: #0F172A; position: fixed; padding-top: 20px; }
        .sidebar a { display: block; color: white; padding: 12px; text-decoration: none; }
        .sidebar a:hover { background-color: #2563EB; }
        .main { margin-left: 230px; padding: 20px; }
        .card { background: #FFFFFF; padding: 20px; margin-bottom: 15px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); border : 1px solid #E2E8F0; }
    </style>
</head>
<body>

<div class="header">
    <h2>Welcome Student - Dashboard</h2>
</div>

<div class="sidebar">
    <a href="dashboard">Home</a>
    <a href="profile_page.html">My Profile</a>
    <a href="application.jsp">Application Form</a>
    <a href="LogoutServlet">Logout</a>
</div>

<div class="main">
    <div class="card">
        <h2>ID : PRPCM${student_id}</h2>
        <h3>Student Name: ${f_name} ${l_name}</h3>
        <p>Email: ${email}</p>
        <p>Mobile: ${mobile}</p>
        <p>Gender: ${gender}</p>
        <p>DOB: ${dob}</p>
    </div>
</div>

</body>
</html>
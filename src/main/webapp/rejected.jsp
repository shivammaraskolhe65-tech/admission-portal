<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.portal.db.DBConnection" %>
<%
    if(session.getAttribute("admin") == null){
        response.sendRedirect("admin.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Rejected Applications</title>
    <style>
        body { margin: 0; font-family: Arial, sans-serif; background-color: #c4edf8; }
        .header { background-color: #1E40AF; color: white; padding: 15px; text-align: center; font-size: 22px; }
        .sidebar { width: 200px; height: 100vh; background-color: #0F172A; position: fixed; padding-top: 20px; }
        .sidebar a { display: block; color: white; padding: 12px; text-decoration: none; }
        .sidebar a:hover { background-color: #055a6b; }
        .main { margin-left: 210px; padding: 20px; }
        table { width: 100%; border-collapse: collapse; background-color: white; }
        table, th, td { border: 1px solid #ccc; }
        th { background-color: #033744; color: white; padding: 10px; }
        td { padding: 8px; text-align: center; }
        .btn { padding: 5px 10px; border: none; cursor: pointer; color: white; text-decoration: none; background-color: #28a745; border-radius:3px; }
    </style>
</head>
<body>

<div class="header">Admin Dashboard</div>

<div class="sidebar">
    <a href="admin_dashboard.jsp">Dashboard</a>
    <a href="approved.jsp">Approved Apps</a>
    <a href="rejected.jsp">Rejected Apps</a>
    <a href="LogoutServlet">Logout</a>
</div>

<div class="main">
    <h2>Rejected Student Applications</h2>
    <table>
        <tr>
            <th>SR.NO.</th>
            <th>Student ID</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>Phone No</th>
            <th>Email</th>
            <th>Course</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            int count = 1;
            try {
                Connection con = DBConnection.getConnection();
                String query = "SELECT a.application_id, a.student_id, s.f_name, s.l_name, s.mobile, s.email, c.course_name, a.status " +
                               "FROM application_tb a " +
                               "JOIN student_tb s ON a.student_id = s.student_id " +
                               "JOIN course_tb c ON a.course_id = c.course_id " +
                               "WHERE a.status='Rejected'";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
        %>
        <tr>
            <td><%= count++ %></td>
            <td><%= rs.getInt("student_id") %></td>
            <td><%= rs.getString("f_name") %></td>
            <td><%= rs.getString("l_name") %></td>
            <td><%= rs.getString("mobile") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("course_name") %></td>
            <td style="color:red; font-weight:bold;"><%= rs.getString("status") %></td>
            <td>
                <a class="btn" href="UpdateStatusServlet?id=<%= rs.getInt("application_id") %>&status=Approved">Approve</a>
            </td>
        </tr>
        <%
                }
            } catch(Exception e){ e.printStackTrace(); }
        %>
    </table>
</div>

</body>
</html>
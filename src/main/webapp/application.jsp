<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.portal.db.DBConnection" %>
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
    <title>Apply for Admission</title>
    <style>
        body { margin: 0; font-family: Arial, sans-serif; background-color: #F8FAFC; }
        .header { background-color: #1E40AF; color: white; padding: 15px; text-align: center; }
        .sidebar { width: 220px; height: 100vh; background-color: #0F172A; position: fixed; padding-top: 20px; }
        .sidebar a { display: block; color: white; padding: 12px; text-decoration: none; }
        .sidebar a:hover { background-color: #1abc9c; }
        .main { margin-left: 230px; padding: 20px; }
        .card { background: white; padding: 20px; margin-bottom: 15px; border-radius: 8px; background-color:#aee2f1; width: 60%; }
        select { width: 100%; padding: 10px; margin-top: 10px; border-radius: 5px; }
        button { padding: 10px 20px; background-color: #570244; color: white; border: none; border-radius: 5px; cursor: pointer; margin-top: 10px; }
        button:hover { background-color: #16a085; }
    </style>
    <script>
        function loadCourses() {
            let instituteId = document.getElementById("institute").value;
            let courseSelect = document.getElementById("course");
            courseSelect.innerHTML = "<option value=''>Select Course</option>";
            document.getElementById("seatInfo").innerText = "Available Seats: --";

            if (!instituteId) return;

            // Context path stripped to make integration seamless across machines
            fetch("GetCoursesServlet?institute_id=" + instituteId)
                .then(res => res.json())
                .then(data => {
                    data.forEach(c => {
                        let option = document.createElement("option");
                        option.value = c.id;
                        option.text = c.name;
                        courseSelect.appendChild(option);
                    });
                })
                .catch(err => console.error("Error loading courses:", err));
        }

        function loadSeats() {
            let courseId = document.getElementById("course").value;
            if (!courseId) {
                document.getElementById("seatInfo").innerText = "Available Seats: --";
                return;
            }

            fetch("GetSeatsServlet?course_id=" + courseId)
                .then(res => res.text())
                .then(data => {
                    document.getElementById("seatInfo").innerText = "Available Seats: " + data;
                })
                .catch(err => console.error("Error loading seats:", err));
        }
    </script>
</head>
<body>

<div class="header">
    <h2>Course Application Desk</h2>
</div>

<div class="sidebar">
    <a href="dashboard">Home</a>
    <a href="profile_page.html">My Profile</a>
    <a href="application.jsp">Application Form</a>
    <a href="LogoutServlet">Logout</a>
</div>

<div class="main">
    <form action="ApplicationServlet" method="post">
        <div class="card">
            <label><b>Select Institute:</b></label>
            <select name="institute_id" id="institute" onchange="loadCourses()" required>
                <option value="">Select Institute</option>
                <%
                    try {
                        Connection con = DBConnection.getConnection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM institute_tb");
                        while(rs.next()){
                %>
                <option value="<%= rs.getInt("institute_id") %>"><%= rs.getString("insti_name") %></option>
                <% 
                        }
                    } catch(Exception e) { e.printStackTrace(); }
                %>
            </select>
            <br><br>

            <label><b>Select Course:</b></label>
            <select name="course_id" id="course" onchange="loadSeats()" required>
                <option value="">Select Course</option>
            </select>
        </div>

        <div class="card">
            <h4 id="seatInfo" style="margin:0;">Available Seats: --</h4>
        </div>

        <button type="submit">Submit Application</button>
    </form>
</div>

</body>
</html>
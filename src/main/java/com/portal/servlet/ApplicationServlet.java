package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class ApplicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("student_id");

        if (studentId == null) {
            response.sendRedirect("index.html");
            return;
        }

        String courseIdStr = request.getParameter("course_id");

        if (courseIdStr == null || courseIdStr.isEmpty()) {
            response.getWriter().println("Course selection is missing.");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO application_tb(student_id, course_id, apply_date, status) VALUES (?, ?, CURDATE(), 'Pending')";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, Integer.parseInt(courseIdStr));

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                response.sendRedirect("dashboard");
            } else {
                response.getWriter().println("Application insertion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("HTTP GET Method is not supported for this endpoint. Use POST.");
    }
}
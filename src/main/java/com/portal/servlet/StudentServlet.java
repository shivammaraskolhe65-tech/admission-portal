package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fname = request.getParameter("first_name");
        String lname = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");

        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO student_tb(f_name, l_name, email, password, mobile, gender, DOB) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, mobile);
            ps.setString(6, gender);
            ps.setString(7, dob);

            ps.executeUpdate();
            response.sendRedirect("index.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Registration error occurred: " + e.getMessage());
        }
    }
}
package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM student_tb WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("student_id", rs.getInt("student_id"));
                session.setAttribute("f_name", rs.getString("f_name"));
                session.setAttribute("l_name", rs.getString("l_name"));
                session.setAttribute("email", rs.getString("email"));
                session.setAttribute("mobile", rs.getString("mobile"));
                session.setAttribute("gender", rs.getString("gender"));
                session.setAttribute("dob", rs.getString("DOB"));

                response.sendRedirect("dashboard");
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<h3 style='color:red;'>Invalid Student Login Credentials</h3>");
                response.getWriter().println("<a href='index.html'>Go Back</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
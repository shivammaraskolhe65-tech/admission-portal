package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM admin_tb WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", username);
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<h3 style='color:red;'>Invalid Admin Credentials</h3>");
                response.getWriter().println("<a href='admin.html'>Try Again</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
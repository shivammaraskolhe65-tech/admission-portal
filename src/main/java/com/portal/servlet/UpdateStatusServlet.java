package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class UpdateStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String status = request.getParameter("status");

        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE application_tb SET status=? WHERE application_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, Integer.parseInt(id));
            ps.executeUpdate();

            response.sendRedirect("admin_dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error updating application tracking visibility context.");
        }
    }
}
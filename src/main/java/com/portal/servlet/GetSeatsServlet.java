package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class GetSeatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        String courseId = request.getParameter("course_id");

        if (courseId == null || courseId.isEmpty()) {
            response.getWriter().print("0");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT total_seats FROM course_tb WHERE course_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(courseId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                response.getWriter().print(rs.getInt("total_seats"));
            } else {
                response.getWriter().print("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Error");
        }
    }
}
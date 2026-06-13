package com.portal.servlet;

import com.portal.db.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class GetCoursesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String instituteId = request.getParameter("institute_id");
        response.setContentType("application/json");

        if (instituteId == null || instituteId.isEmpty()) {
            response.getWriter().write("[]");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT course_id, course_name FROM course_tb WHERE institute_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(instituteId));
            ResultSet rs = ps.executeQuery();

            StringBuilder json = new StringBuilder();
            json.append("[");
            while (rs.next()) {
                json.append("{")
                    .append("\"id\":").append(rs.getInt("course_id")).append(",")
                    .append("\"name\":\"").append(rs.getString("course_name")).append("\"")
                    .append("},");
            }
            if (json.length() > 1) {
                json.setLength(json.length() - 1);
            }
            json.append("]");
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("[]");
        }
    }
}
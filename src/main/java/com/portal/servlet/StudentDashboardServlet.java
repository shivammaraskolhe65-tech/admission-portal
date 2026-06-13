package com.portal.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class StudentDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("student_id");

        if (studentId == null) {
            response.sendRedirect("index.html");
            return;
        }

        request.setAttribute("f_name", session.getAttribute("f_name"));
        request.setAttribute("l_name", session.getAttribute("l_name"));
        request.setAttribute("email", session.getAttribute("email"));
        request.setAttribute("mobile", session.getAttribute("mobile"));
        request.setAttribute("gender", session.getAttribute("gender"));
        request.setAttribute("dob", session.getAttribute("dob"));

        RequestDispatcher rd = request.getRequestDispatcher("student_dashboard.jsp");
        rd.forward(request, response);
    }
}
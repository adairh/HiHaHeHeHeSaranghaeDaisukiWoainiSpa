package me.sjihh.spaservice.Servlet.Admin.Staff;

import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Database.StaffLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoadStaff")
public class LoadStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            StaffLoader staff = StaffLoader.getStaffById(id);

            assert staff != null;
            String json = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"serviceID\": \"" + staff.getService_ID() + "\"," +
                    "\"staffName\": \"" + staff.getStaff_name() + "\"" +
                    "}";

            out.println(json.replaceAll("\\r?\\n|\\r", ""));
        }

        response.sendRedirect("/admin/staff.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        // Your code for handling POST requests goes here
    }
}

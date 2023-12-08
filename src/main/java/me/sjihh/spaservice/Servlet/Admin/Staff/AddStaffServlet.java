package me.sjihh.spaservice.Servlet.Admin.Staff;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/AddStaff")
public class AddStaffServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get input parameters
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        String staffName = request.getParameter("staffName");

        try {
            // Get DB connection
            Connection conn = SQLConnection.getConnection();

            // Create query
            String sql = "INSERT INTO staff (service_ID, staff_name) VALUES (?, ?)";

            // Set parameters
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, serviceID);
            statement.setString(2, staffName);

            // Execute insert
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/staff.jsp");
    }
}

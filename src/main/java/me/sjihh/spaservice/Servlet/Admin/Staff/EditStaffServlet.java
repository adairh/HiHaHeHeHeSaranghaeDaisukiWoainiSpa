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

@WebServlet("/admin/EditStaff")
public class EditStaffServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        int staffID = Integer.parseInt(request.getParameter("id"));
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        String staffName = request.getParameter("staffName");

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "UPDATE staff SET staff_ID = ?, service_ID = ?, staff_name = ? WHERE staff_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, staffID);
            statement.setInt(2, serviceID);
            statement.setString(3, staffName);
            statement.setInt(4, staffID);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/staff.jsp");
    }
}

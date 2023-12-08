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

@WebServlet("/DeleteStaff")
public class DeleteStaffServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "DELETE FROM staff WHERE staff_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/staff.jsp");
    }
}

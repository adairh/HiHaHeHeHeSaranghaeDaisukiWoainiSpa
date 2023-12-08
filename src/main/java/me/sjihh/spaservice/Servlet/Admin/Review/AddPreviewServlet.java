package me.sjihh.spaservice.Servlet.Admin.Review;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/AddPreview")
public class AddPreviewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get input parameters
        int adminID = 1;
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        String comment = request.getParameter("comment");

        try {
            // Get DB connection
            Connection conn = SQLConnection.getConnection();

            // Create query
            String sql = "INSERT INTO preview (admin_ID, customer_ID, service_ID, comment) VALUES (?, ?, ?, ?)";

            // Set parameters
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, adminID);
            statement.setInt(2, customerID);
            statement.setInt(3, serviceID);
            statement.setString(4, comment);

            // Execute insert
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/preview.jsp");
    }
}

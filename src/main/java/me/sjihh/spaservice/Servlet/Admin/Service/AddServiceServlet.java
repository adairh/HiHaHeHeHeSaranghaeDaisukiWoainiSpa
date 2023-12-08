package me.sjihh.spaservice.Servlet.Admin.Service;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/AddService")
public class AddServiceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get input parameters
        int adminID = 1;
        String serviceName = request.getParameter("serviceName");
        int servicePrice = Integer.parseInt(request.getParameter("servicePrice"));
        int serviceTime = Integer.parseInt(request.getParameter("serviceTime"));
        String serviceDetail = request.getParameter("serviceDetail");

        try {
            // Get DB connection
            Connection conn = SQLConnection.getConnection();

            // Create query
            String sql = "INSERT INTO service (admin_ID, service_name, service_price, service_time, service_detail) VALUES (?, ?, ?, ?, ?)";

            // Set parameters
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, adminID);
            statement.setString(2, serviceName);
            statement.setInt(3, servicePrice);
            statement.setInt(4, serviceTime);
            statement.setString(5, serviceDetail);

            // Execute insert
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/services.jsp");
    }
}

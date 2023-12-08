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

@WebServlet("/admin/EditService")
public class EditServiceServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        int serviceID = Integer.parseInt(request.getParameter("id"));
        int adminID = 1;
        String serviceName = request.getParameter("serviceName");
        int servicePrice = Integer.parseInt(request.getParameter("servicePrice"));
        int serviceTime = Integer.parseInt(request.getParameter("serviceTime"));
        String serviceDetail = request.getParameter("serviceDetail");

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "UPDATE service SET service_ID = ?, admin_ID = ?, service_name = ?, service_price = ?, service_time = ?, service_detail = ? WHERE service_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, serviceID);
            statement.setInt(2, adminID);
            statement.setString(3, serviceName);
            statement.setInt(4, servicePrice);
            statement.setInt(5, serviceTime);
            statement.setString(6, serviceDetail);
            statement.setInt(7, serviceID);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/services.jsp");
    }
}

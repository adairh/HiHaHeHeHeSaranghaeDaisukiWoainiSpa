package me.sjihh.spaservice.Servlet.Admin.Customer;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/AddCustomer")
public class AddCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get input parameters
        int levelID = Integer.parseInt(request.getParameter("levelID"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        try {
            // Get DB connection
            Connection conn = SQLConnection.getConnection();

            // Create query
            String sql = "INSERT INTO customer (level_ID, username, pass, address, telephone, email) VALUES (?, ?, ?, ?, ?, ?)";

            // Set parameters
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, levelID);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, address);
            statement.setString(5, telephone);
            statement.setString(6, email);

            // Execute insert
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/customer.jsp");
    }
}

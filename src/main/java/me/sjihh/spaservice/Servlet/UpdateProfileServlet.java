package me.sjihh.spaservice.Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.sjihh.spaservice.Authentication.Customer; 
import me.sjihh.spaservice.Database.SQLConnection;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get updated values from form  
        String username = request.getParameter("username");  
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        // Get current logged in user
        Customer user = (Customer) request.getSession().getAttribute("user");
        int userId = user.getId();
        
        try {
            // Open db connection 
            Connection conn = SQLConnection.getConnection();
            
            String sql = "UPDATE customer SET username = ?, email = ?, telephone = ?, address = ? WHERE customer_ID = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // Set parameters
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone); 
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, userId);
            
            // Execute SQL update query
            preparedStatement.executeUpdate();

            Customer newCus = new Customer(((Customer) request.getSession().getAttribute("user")).getId(), ((Customer) request.getSession().getAttribute("user")).getLevel_id(), username, address, email, phone);

            request.getSession().setAttribute("user", newCus);
            
        } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
        }
        
        // Redirect back to profile 
        response.sendRedirect("profile.jsp");      
    }

}
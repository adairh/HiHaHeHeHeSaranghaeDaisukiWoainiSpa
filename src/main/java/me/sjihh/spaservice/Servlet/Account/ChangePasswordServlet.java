package me.sjihh.spaservice.Servlet.Account;

import me.sjihh.spaservice.Authentication.Admin;
import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Authentication.User;
import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
                            
        String oldPassword = request.getParameter("old_password"); 
        String newPassword = request.getParameter("new_password");

        /* Get logged in user */
        Customer customer = (Customer)request.getSession().getAttribute("user");
        
        // Check old password matches
        if(validatePassword(oldPassword, customer.getId())) {
          
          // Update password
          updatePassword(customer.getId(), newPassword);
          
        } else {
          // Show error message  
        }
        
        response.sendRedirect("profile.jsp");       
    }

    public boolean validatePassword(String old, int id) {
        Customer authenticatedUser = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = SQLConnection.getConnection();
            String query = "SELECT * FROM customer WHERE customer_ID = ? AND pass = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, old);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String pass = resultSet.getString("pass");
                return pass.equals(old);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public void updatePassword(int userId, String newPassword) {

        try {
            // Open db connection
            Connection conn = SQLConnection.getConnection();

            // Hash the new password

            String sql = "UPDATE customer SET pass = ? WHERE customer_ID = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            // Execute update query
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
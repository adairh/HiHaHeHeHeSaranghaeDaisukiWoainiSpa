package me.sjihh.spaservice.Servlet.Account;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Email.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
                            
        // Get email  
        String email = request.getParameter("email");
        
        // Lookup user by email
        Customer customer = Customer.getUserByEmail(email);
        
        if(customer != null) {
           // Generate random password
           String newPassword = generateRandomString(8);
           
           // Update password 
           updatePassword(customer.getId(), newPassword);
           
           // Send email with new password
           EmailSender.sendForgetPasswordEmail(email, newPassword);
           
        }
        
        // Redirect to login page
        response.sendRedirect("sign.jsp");
    }
    
    // DB logic to get user by email is omitted for brevity


    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = UPPER + LOWER + DIGITS;

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(randomIndex));
        }

        return sb.toString();
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

    //

}
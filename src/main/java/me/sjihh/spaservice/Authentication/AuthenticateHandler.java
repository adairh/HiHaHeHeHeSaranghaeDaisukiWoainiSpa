package me.sjihh.spaservice.Authentication;

import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Email.EmailSender;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class AuthenticateHandler {


    public User login(String uname, String password) {
        if (uname.equals("AdminUser@admin.user") && password.equals("admin123")) {
            return new Admin();
        }
        Customer authenticatedUser = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = SQLConnection.getConnection();
            String query = "SELECT * FROM customer WHERE email = ? AND pass = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("customer_ID");
                int lvlID = resultSet.getInt("level_ID");
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("telephone");

                authenticatedUser = new Customer(id, lvlID, username, address, email, phone);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return authenticatedUser;
    }

    public void register(String uname, String password, String email) throws ClassNotFoundException, SQLException {
        if (!isEmailExists(email)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = SQLConnection.getConnection();
                String query = "INSERT INTO customer (" +
                        "level_ID, username, pass, address, telephone, email" +
                        ") VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, uname);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, null);
                preparedStatement.setString(5, null);
                preparedStatement.setString(6, email);
                preparedStatement.executeUpdate();
                conn.close();
                EmailSender.sendRegistrationConfirmationEmail(email, uname);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Handle the case when the email already exists
            System.out.println("Account with this email already exists!");
            // You might want to throw an exception or provide a different response based on your requirements
        }
    }

    private boolean isEmailExists(String email) throws ClassNotFoundException, SQLException {
        boolean exists = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = SQLConnection.getConnection();
            String query = "SELECT COUNT(*) AS count FROM customer WHERE email = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt("count");
                        exists = count > 0;
                    }
                }
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

}

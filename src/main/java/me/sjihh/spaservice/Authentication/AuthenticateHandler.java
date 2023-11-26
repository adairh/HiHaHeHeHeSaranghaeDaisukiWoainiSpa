package me.sjihh.spaservice.Authentication;

import me.sjihh.spaservice.Database.SQLConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class AuthenticateHandler {


    public User login(String uname, String password) {
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
        Customer authenticatedUser = null;
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
            preparedStatement.setString(4, "abc");
            preparedStatement.setString(5, "1");
            preparedStatement.setString(6, email);
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package me.sjihh.spaservice.Authentication;

import me.sjihh.spaservice.Booking.Booking;
import me.sjihh.spaservice.Booking.BookingDetail;
import me.sjihh.spaservice.Database.BookingDetailLoader;
import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Database.ServiceLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    int level_id;
    public Customer(int id, int lvl, String username, String address, String email, String phone) {
        super(id, username, address, email, phone);
        level_id = lvl;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }


    public static Customer getUserById(int userId) {
        Customer customer = null;

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM customer WHERE customer_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("customer_ID");
                        int levelId = resultSet.getInt("level_ID");
                        String username = resultSet.getString("username");
                        String address = resultSet.getString("address");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("telephone");

                        customer = new Customer(id, levelId, username, address, email, phone);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return customer;
    }
    public static Customer getUserByEmail(String mail) {
        Customer customer = null;

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM customer WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, mail);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("customer_ID");
                        int levelId = resultSet.getInt("level_ID");
                        String username = resultSet.getString("username");
                        String address = resultSet.getString("address");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("telephone");

                        customer = new Customer(id, levelId, username, address, email, phone);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM customer";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("customer_ID");
                    int levelId = resultSet.getInt("level_ID");
                    String username = resultSet.getString("username");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("telephone");

                    Customer customer = new Customer(id, levelId, username, address, email, phone);
                    customerList.add(customer);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    public static List<ServiceLoader> getAllServicesBookedByUser(int userId) {
        System.out.println(userId);
        List<ServiceLoader> lsl = new ArrayList<ServiceLoader>();
        System.out.println(lsl.toArray().toString());
        for (Booking b : Booking.getBookingsByCustomerID(userId)) {
            System.out.println(b.getBooking_ID());
            for (BookingDetailLoader bd : BookingDetailLoader.getBookingDetailsByBookingID(b.getBooking_ID())) {
                System.out.println("A" + bd.getService_ID());
                if (!lsl.contains(ServiceLoader.loadServices().get(bd.getService_ID() - 1))) {
                    System.out.println(ServiceLoader.loadServices().get(bd.getService_ID() - 1).getService_name() + " " + lsl.size());
                    lsl.add(ServiceLoader.loadServices().get(bd.getService_ID() - 1));
                }
            }
        }
        return lsl;
    }

}

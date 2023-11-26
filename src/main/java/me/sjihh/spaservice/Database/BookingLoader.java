package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingLoader {
    private int booking_ID;
    private int customer_ID;
    private java.sql.Timestamp booking_date;
    private int total;

    public BookingLoader(int booking_ID, int customer_ID, java.sql.Timestamp booking_date, int total) {
        this.booking_ID = booking_ID;
        this.customer_ID = customer_ID;
        this.booking_date = booking_date;
        this.total = total;
    }

    public static BookingLoader getBookingByID(int bookingID) {
        BookingLoader bookingLoader = null;

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM booking WHERE booking_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookingID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int customer_ID = resultSet.getInt("customer_ID");
                        java.sql.Timestamp booking_date = resultSet.getTimestamp("booking_date");
                        int total = resultSet.getInt("total");

                        bookingLoader = new BookingLoader(bookingID, customer_ID, booking_date, total);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingLoader;
    }
    public static List<BookingLoader> getBookingsByCustomerID(int customerID) {
        List<BookingLoader> bookingLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM booking WHERE customer_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, customerID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int booking_ID = resultSet.getInt("booking_ID");
                        java.sql.Timestamp booking_date = resultSet.getTimestamp("booking_date");
                        int total = resultSet.getInt("total");

                        BookingLoader bookingLoader = new BookingLoader(booking_ID, customerID, booking_date, total);
                        bookingLoaders.add(bookingLoader);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingLoaders;
    }


    public static List<BookingLoader> loadBookings() {
        List<BookingLoader> bookingLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM booking";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int booking_ID = resultSet.getInt("booking_ID");
                    int customer_ID = resultSet.getInt("customer_ID");
                    java.sql.Timestamp booking_date = resultSet.getTimestamp("booking_date");
                    int total = resultSet.getInt("total");

                    BookingLoader bookingLoader = new BookingLoader(booking_ID, customer_ID, booking_date, total);
                    bookingLoaders.add(bookingLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingLoaders;
    }

    public int getBooking_ID() {
        return booking_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public java.sql.Timestamp getBooking_date() {
        return booking_date;
    }

    public int getTotal() {
        return total;
    }
}

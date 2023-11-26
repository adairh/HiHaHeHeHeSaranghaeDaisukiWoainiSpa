package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDetailLoader {
    private int booking_ID;
    private int customer_ID;
    private int service_ID;
    private int room_ID;
    private java.sql.Timestamp start_service;
    private int staff_ID;
    private int saleOff_ID;
    private java.sql.Timestamp finish_service;

    public BookingDetailLoader(int booking_ID, int customer_ID, int service_ID, int room_ID,
                               java.sql.Timestamp start_service, int staff_ID, int saleOff_ID,
                               java.sql.Timestamp finish_service) {
        this.booking_ID = booking_ID;
        this.customer_ID = customer_ID;
        this.service_ID = service_ID;
        this.room_ID = room_ID;
        this.start_service = start_service;
        this.staff_ID = staff_ID;
        this.saleOff_ID = saleOff_ID;
        this.finish_service = finish_service;
    }

    public static BookingDetailLoader getBookingDetailByID(int bookingDetailID) {
        BookingDetailLoader bookingDetailLoader = null;

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM booking_detail WHERE booking_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookingDetailID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int customer_ID = resultSet.getInt("customer_ID");
                        int service_ID = resultSet.getInt("service_ID");
                        int room_ID = resultSet.getInt("room_ID");
                        java.sql.Timestamp start_service = resultSet.getTimestamp("start_service");
                        int staff_ID = resultSet.getInt("staff_ID");
                        int saleOff_ID = resultSet.getInt("saleOff_ID");
                        java.sql.Timestamp finish_service = resultSet.getTimestamp("finish_service");

                        bookingDetailLoader = new BookingDetailLoader(bookingDetailID, customer_ID, service_ID,
                                room_ID, start_service, staff_ID, saleOff_ID, finish_service);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingDetailLoader;
    }

    public static List<BookingDetailLoader> getBookingDetailsByBookingID(int bookingID) {
        List<BookingDetailLoader> bookingDetailLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM booking_detail WHERE booking_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookingID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int customer_ID = resultSet.getInt("customer_ID");
                        int service_ID = resultSet.getInt("service_ID");
                        int room_ID = resultSet.getInt("room_ID");
                        java.sql.Timestamp start_service = resultSet.getTimestamp("start_service");
                        int staff_ID = resultSet.getInt("staff_ID");
                        int saleOff_ID = resultSet.getInt("saleOff_ID");
                        java.sql.Timestamp finish_service = resultSet.getTimestamp("finish_service");

                        BookingDetailLoader bookingDetailLoader = new BookingDetailLoader(bookingID, customer_ID,
                                service_ID, room_ID, start_service, staff_ID, saleOff_ID, finish_service);
                        bookingDetailLoaders.add(bookingDetailLoader);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingDetailLoaders;
    }


    public static List<BookingDetailLoader> loadBookingDetails() {
        List<BookingDetailLoader> bookingDetailLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM booking_detail";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int booking_ID = resultSet.getInt("booking_ID");
                    int customer_ID = resultSet.getInt("customer_ID");
                    int service_ID = resultSet.getInt("service_ID");
                    int room_ID = resultSet.getInt("room_ID");
                    java.sql.Timestamp start_service = resultSet.getTimestamp("start_service");
                    int staff_ID = resultSet.getInt("staff_ID");
                    int saleOff_ID = resultSet.getInt("saleOff_ID");
                    java.sql.Timestamp finish_service = resultSet.getTimestamp("finish_service");

                    BookingDetailLoader bookingDetailLoader = new BookingDetailLoader(booking_ID, customer_ID,
                            service_ID, room_ID, start_service, staff_ID, saleOff_ID, finish_service);
                    bookingDetailLoaders.add(bookingDetailLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingDetailLoaders;
    }

    public int getBooking_ID() {
        return booking_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public int getService_ID() {
        return service_ID;
    }

    public int getRoom_ID() {
        return room_ID;
    }

    public java.sql.Timestamp getStart_service() {
        return start_service;
    }

    public int getStaff_ID() {
        return staff_ID;
    }

    public int getSaleOff_ID() {
        return saleOff_ID;
    }

    public java.sql.Timestamp getFinish_service() {
        return finish_service;
    }
}

package me.sjihh.spaservice.Database;

import me.sjihh.spaservice.Booking.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceLoader {
    private int service_ID;
    private int admin_ID;
    private String service_name;
    private int service_price;
    private int service_time;

    private String service_detail;

    public ServiceLoader(int service_ID, int admin_ID, String service_name, int service_price, int service_time, String service_detail) {
        this.service_ID = service_ID;
        this.admin_ID = admin_ID;
        this.service_name = service_name;
        this.service_price = service_price;
        this.service_time = service_time;
        this.service_detail = service_detail;
    }

    public static List<ServiceLoader> bookedServiceByUser(int userID) {

        List<ServiceLoader> serviceLoaders = new ArrayList<>();
        for (Booking booking : Booking.getBookingsByCustomerID(userID)) {
            for (BookingDetailLoader bookingDetailLoader : BookingDetailLoader.getBookingDetailsByBookingID(booking.getBooking_ID())) {
                serviceLoaders.add(ServiceLoader.getServiceById(bookingDetailLoader.getService_ID()));
            }
        }
        return serviceLoaders;
    }

    public static boolean isBooked(int userID, int service) {
        List<ServiceLoader> serviceLoaders = bookedServiceByUser(userID);
        for (ServiceLoader serviceLoader : serviceLoaders) {
            if (service == serviceLoader.getService_ID()) {
                return true;
            }
        }
        return false;
    }

    public static List<ServiceLoader> loadServices() {
        List<ServiceLoader> serviceLoaders = new ArrayList<>();



        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM service";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int service_ID = resultSet.getInt("service_ID");
                    int admin_ID = resultSet.getInt("admin_ID");
                    String service_name = resultSet.getString("service_name");
                    int service_price = resultSet.getInt("service_price");
                    int service_time = resultSet.getInt("service_time");
                    String service_detail = resultSet.getString("service_detail");

                    ServiceLoader serviceLoader = new ServiceLoader(service_ID, admin_ID, service_name, service_price, service_time, service_detail);
                    serviceLoaders.add(serviceLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return serviceLoaders;
    }
    public static ServiceLoader getServiceById(int id) {
        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM service WHERE service_ID = ? LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToService(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null; // Return null if no staff is found
    }

    private static ServiceLoader mapResultSetToService(ResultSet resultSet) throws SQLException {
        int service_ID = resultSet.getInt("service_ID");
        int admin_ID = resultSet.getInt("admin_ID");
        String service_name = resultSet.getString("service_name");
        int service_price = resultSet.getInt("service_price");
        int service_time = resultSet.getInt("service_time");
        String service_detail = resultSet.getString("service_detail");



        return new ServiceLoader(service_ID, admin_ID, service_name, service_price, service_time, service_detail);
    }


    public int getService_ID() {
        return service_ID;
    }

    public int getAdmin_ID() {
        return admin_ID;
    }

    public int getService_price() {
        return service_price;
    }

    public int getService_time() {
        return service_time;
    }

    public String getService_name() {
        return service_name;
    }

    public String getService_detail() {
        return service_detail;
    }
}
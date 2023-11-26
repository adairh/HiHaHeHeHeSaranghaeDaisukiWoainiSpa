package me.sjihh.spaservice.Servlet;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Booking.BookingDetail;
import me.sjihh.spaservice.Database.ServiceLoader;
import me.sjihh.spaservice.Database.StaffLoader;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the list of booking details from the request
        List<BookingDetail> bookingDetails = (List<BookingDetail>) request.getSession().getAttribute("services");

        int customerId = ((Customer)request.getSession().getAttribute("user")).getId();
        int total = calculateTotal(bookingDetails); // Implement this method as needed

        // Insert a new booking into the booking table
        int bookingId = insertBooking(customerId, total);

        // Insert booking details into the booking_detail table
        insertBookingDetails(bookingDetails, bookingId,
                customerId,
                (Integer)request.getSession().getAttribute("room"));

        // Redirect to a success page or handle the response as needed
        response.sendRedirect("profile.jsp");
    }

    // Helper method to calculate the total based on the list of booking details
    private int calculateTotal(List<BookingDetail> bookingDetails) {
        // Implement this method based on your business logic
        // For example, summing up the prices of all services
        int total = 0;
        for (BookingDetail bookingDetail : bookingDetails) {
            total += ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_price(); // Replace with the actual method to get the price
        }
        return total;
    }

    // Helper method to insert a new booking into the booking table
    private int insertBooking(int customerId, int total) {
        int bookingId = -1;
        try (Connection connection = SQLConnection.getConnection()) {
            String query = "INSERT INTO booking (customer_ID, booking_date, total) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setInt(3, total);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    // Retrieve the generated booking ID
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            bookingId = generatedKeys.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return bookingId;
    }

    // Helper method to insert booking details into the booking_detail table
    private void insertBookingDetails(List<BookingDetail> bookingDetails, int bookingId, int cus, int room) {
        try (Connection connection = SQLConnection.getConnection()) {
            String query = "INSERT INTO booking_detail (booking_ID, customer_ID, service_ID, room_ID, start_service, staff_ID, saleOff_ID, finish_service) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                LocalDateTime dt = LocalDateTime.now();
                for (BookingDetail bookingDetail : bookingDetails) {
                    preparedStatement.setInt(1, bookingId);
                    preparedStatement.setInt(2, cus);
                    preparedStatement.setInt(3, bookingDetail.getService_ID());
                    preparedStatement.setInt(4, room);
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(dt));
                    preparedStatement.setInt(6, StaffLoader.getStaffByServiceId(bookingDetail.getService_ID()).getStaff_ID());
                    preparedStatement.setInt(7, bookingDetail.getSaleOff_ID());
                    dt = dt.plus(ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_time(), ChronoUnit.MINUTES);
                    preparedStatement.setTimestamp(8, Timestamp.valueOf(dt));

                    preparedStatement.addBatch(); // Add the batch for execution
                }

                // Execute the batch
                int[] affectedRows = preparedStatement.executeBatch();

                // Handle the result of batch execution as needed
                for (int rows : affectedRows) {
                    if (rows <= 0) {
                        // Handle the failed insertions
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}

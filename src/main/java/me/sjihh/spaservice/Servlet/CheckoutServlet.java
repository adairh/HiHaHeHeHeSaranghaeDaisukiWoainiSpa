package me.sjihh.spaservice.Servlet;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Booking.Booking;
import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Booking.BookingDetail;
import me.sjihh.spaservice.Database.ServiceLoader;
import me.sjihh.spaservice.Database.StaffLoader;
import me.sjihh.spaservice.Email.EmailSender;

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
        String dt = (String) request.getSession().getAttribute("time");
        LocalDateTime date = LocalDateTime.parse(dt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int total = (int) request.getSession().getAttribute("finalPrice"); // Implement this method as needed

        // Insert a new booking into the booking table
        int bookingId = insertBooking(customerId, date, total);

        // Insert booking details into the booking_detail table
        insertBookingDetails(bookingDetails, bookingId,
                customerId,
                (Integer)request.getSession().getAttribute("room"));

        EmailSender.sendBookingConfirmationEmail(((Customer) request.getSession().getAttribute("user")).getEmail(), Booking.getBookingByID(bookingId), bookingDetails);

        request.getSession().setAttribute("services", null);

        // Redirect to a success page or handle the response as needed
        response.sendRedirect("profile.jsp");
    }

    // Helper method to calculate the total based on the list of booking details

    // Helper method to insert a new booking into the booking table
    private int insertBooking(int customerId, LocalDateTime date, int total) {
        int bookingId = -1;
        try (Connection connection = SQLConnection.getConnection()) {
            String query = "INSERT INTO booking (customer_ID, booking_date, total) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.setTimestamp(2, Timestamp.valueOf(date));
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
                    System.out.println("BOOKING: " + bookingDetail.getService_ID());
                    preparedStatement.setInt(1, bookingId);
                    preparedStatement.setInt(2, cus);
                    preparedStatement.setInt(3, bookingDetail.getService_ID());
                    preparedStatement.setInt(4, room);
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(dt));
                    preparedStatement.setInt(6, StaffLoader.getStaffByServiceId(bookingDetail.getService_ID()).getStaff_ID());
                    preparedStatement.setInt(7, bookingDetail.getSaleOff_ID());
                    dt = dt.plus(ServiceLoader.loadServices().get(bookingDetail.getService_ID()-1).getService_time(), ChronoUnit.MINUTES);
                    preparedStatement.setTimestamp(8, Timestamp.valueOf(dt));

                    preparedStatement.addBatch(); // Add the batch for execution
                }

                // Execute the batch
                int[] affectedRows = preparedStatement.executeBatch();

                for (int rows : affectedRows) {
                    if (rows <= 0) {
                        // Handle the failed insertions
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

// Handle the result of batch execution as needed
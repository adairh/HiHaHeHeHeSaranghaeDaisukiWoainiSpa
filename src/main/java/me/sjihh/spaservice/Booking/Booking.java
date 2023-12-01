    package me.sjihh.spaservice.Booking;

    import me.sjihh.spaservice.Database.SQLConnection;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    public class Booking {
        int booking_ID;
        int customer_ID;
        Date booking_date;
        int total;
        boolean status;

        public Booking(int bookingID, int customerId, Timestamp bookingDate, int total) {
            this.booking_ID = bookingID;
            this.customer_ID = customerId;
            this.booking_date = bookingDate;
            this.total = total;
        }

        //================================================================

        public Date getBooking_date() {
            return booking_date;
        }

        public int getBooking_ID() {
            return booking_ID;
        }

        public int getCustomer_ID() {
            return customer_ID;
        }

        public int getTotal() {
            return total;
        }

        public boolean isStatus() {
            return status;
        }

        public static Booking getBookingByID(int bookingID) {
            Booking bookingLoader = null;

            try (Connection connection = SQLConnection.getConnection()) {
                String query = "SELECT * FROM booking WHERE booking_ID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, bookingID);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            int customer_ID = resultSet.getInt("customer_ID");
                            java.sql.Timestamp booking_date = resultSet.getTimestamp("booking_date");
                            int total = resultSet.getInt("total");

                            bookingLoader = new Booking(bookingID, customer_ID, booking_date, total);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return bookingLoader;
        }
        public static List<Booking> getBookingsByCustomerID(int customerID) {
            List<Booking> bookingLoaders = new ArrayList<>();

            try (Connection connection = SQLConnection.getConnection()) {
                String query = "SELECT * FROM booking WHERE customer_ID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, customerID);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            int booking_ID = resultSet.getInt("booking_ID");
                            java.sql.Timestamp booking_date = resultSet.getTimestamp("booking_date");
                            int total = resultSet.getInt("total");

                            Booking bookingLoader = new Booking(booking_ID, customerID, booking_date, total);
                            bookingLoaders.add(bookingLoader);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return bookingLoaders;
        }


        public static List<Booking> loadBookings() {
            List<Booking> bookingLoaders = new ArrayList<>();

            try (Connection connection = SQLConnection.getConnection()) {
                String query = "SELECT * FROM booking";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        int booking_ID = resultSet.getInt("booking_ID");
                        int customer_ID = resultSet.getInt("customer_ID");
                        java.sql.Timestamp booking_date = resultSet.getTimestamp("booking_date");
                        int total = resultSet.getInt("total");

                        Booking bookingLoader = new Booking(booking_ID, customer_ID, booking_date, total);
                        bookingLoaders.add(bookingLoader);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return bookingLoaders;
        }

        //================================================================



        public void setBooking_date(Date booking_date) {
            this.booking_date = booking_date;
        }

        public void setBooking_ID(int booking_ID) {
            this.booking_ID = booking_ID;
        }

        public void setCustomer_ID(int customer_ID) {
            this.customer_ID = customer_ID;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

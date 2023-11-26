package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Booking.BookingDetail;
import me.sjihh.spaservice.Database.ServiceLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "booking", value = "/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String name = request.getParameter("service");
        int serviceID = Integer.parseInt(name);
        ServiceLoader s = ServiceLoader.loadServices().get(serviceID);

        List<BookingDetail> bookings;

        if (request.getSession().getAttribute("services") == null) {
            bookings = new ArrayList<>();
        }
        else {
            bookings = (List<BookingDetail>) request.getSession().getAttribute("services");



        }

        BookingDetail bd = new BookingDetail();
        bd.setCustomer_ID(((Customer)session.getAttribute("user")).getId());
        bd.setService_ID(serviceID);

        boolean f = false;

        for (BookingDetail booking : bookings) {
            if (booking.getService_ID() != serviceID) {
                continue;
            }
            else {
                f = true;
            }
        }


        if (!f) {
            bookings.add(bd);
            f = false;
        }



        session.setAttribute("services", bookings);

        getServletContext()
                .getRequestDispatcher("/booknow.jsp")
                .forward(request, response);


        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = SQLConnection.getConnection();
            String query = "select * from booking where booking_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, "1");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println(resultSet.getInt("status"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("service");
        int serviceID = Integer.parseInt(name);
        ServiceLoader s = ServiceLoader.loadServices().get(serviceID);

        List<BookingDetail> bookings = (List<BookingDetail>) request.getSession().getAttribute("services");

        int totalCost = 0;

        for (BookingDetail book : bookings) {
            totalCost += ServiceLoader.loadServices().get(book.getService_ID()).getService_price();
        }

        //add booking with customer id, get the booking id, add in the booking details with each booking details
    }
}

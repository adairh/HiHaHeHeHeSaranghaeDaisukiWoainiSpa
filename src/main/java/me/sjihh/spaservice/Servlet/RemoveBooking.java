package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Booking.BookingDetail;
import me.sjihh.spaservice.Database.ServiceLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "RemoveBooking", value = "/RemoveBooking")
public class RemoveBooking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String name = request.getParameter("service");
        int serviceID = Integer.parseInt(name);
        ServiceLoader s = ServiceLoader.loadServices().get(serviceID);

        List<BookingDetail> bookings;


        if (request.getSession().getAttribute("services") != null) {
            bookings = (List<BookingDetail>) request.getSession().getAttribute("services");
            Iterator<BookingDetail> iterator = bookings.iterator();
            while (iterator.hasNext()) {
                BookingDetail bookingDetail = iterator.next();
                if (bookingDetail.getService_ID() == serviceID) {
                    iterator.remove();
                }
            }

            session.setAttribute("services", bookings);
            getServletContext()
                    .getRequestDispatcher("/cart.jsp")
                    .forward(request, response);

        }
    }
}

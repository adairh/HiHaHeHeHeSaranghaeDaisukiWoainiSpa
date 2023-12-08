package me.sjihh.spaservice.Booking;

import me.sjihh.spaservice.Database.ServiceLoader;

import java.util.List;

public class BookingHandle {

    public static double getTotalPrice(List<BookingDetail> bookingDetails) {
        double p = 0;
        for (BookingDetail b : bookingDetails) {
            p += ServiceLoader.getServiceById(b.getService_ID()).getService_price();
        }
        return p;
    }
}

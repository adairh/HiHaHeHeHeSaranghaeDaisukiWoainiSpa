package me.sjihh.spaservice.Booking;

import me.sjihh.spaservice.Database.ServiceLoader;

import java.util.List;

public class BookingHandle {

    public static double getTotalPrice(List<BookingDetail> bookingDetails) {
        double p = 0;
        for (BookingDetail b : bookingDetails) {
            p += ServiceLoader.loadServices().get(b.getService_ID()-1).getService_price();
        }
        return p * 1000;
    }
}

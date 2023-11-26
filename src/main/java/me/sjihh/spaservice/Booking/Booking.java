package me.sjihh.spaservice.Booking;

import java.util.Date;

public class Booking {
    int booking_ID;
    int customer_ID;
    Date booking_date;
    int total;
    boolean status;

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

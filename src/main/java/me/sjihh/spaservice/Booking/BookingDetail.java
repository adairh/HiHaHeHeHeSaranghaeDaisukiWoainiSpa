package me.sjihh.spaservice.Booking;

public class BookingDetail
{
    int booking_ID;
    int customer_ID;
    int service_ID;
    int sale_ID;
    int saleOff_ID;
    int room_ID;

    public BookingDetail() {
        this.booking_ID = -1;
        this.customer_ID = -1;
        this.service_ID = -1;
        this.sale_ID = -1;
        this.saleOff_ID = -1;
        this.room_ID = -1;
    }

    public int getBooking_ID() {
        return booking_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public int getRoom_ID() {
        return room_ID;
    }

    public int getSale_ID() {
        return sale_ID;
    }

    public int getSaleOff_ID() {
        return saleOff_ID;
    }

    public int getService_ID() {
        return service_ID;
    }

    public void setBooking_ID(int booking_ID) {
        this.booking_ID = booking_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public void setRoom_ID(int room_ID) {
        this.room_ID = room_ID;
    }

    public void setSale_ID(int sale_ID) {
        this.sale_ID = sale_ID;
    }

    public void setSaleOff_ID(int saleOff_ID) {
        this.saleOff_ID = saleOff_ID;
    }

    public void setService_ID(int service_ID) {
        this.service_ID = service_ID;
    }
}

package me.sjihh.spaservice.Booking;

public class BookingDetail
{
    private int booking_ID;
    private int customer_ID;
    private int service_ID;
    private int room_ID;
    private int staff_ID;
    private int saleOff_ID;

    public BookingDetail() {
        this.booking_ID = -1;
        this.customer_ID = -1;
        this.service_ID = -1;
        this.saleOff_ID = -1;
        this.room_ID = -1;
        this.staff_ID = -1;
    }

    public int getStaff_ID() {
        return staff_ID;
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

    public void setSaleOff_ID(int sale_ID) {
        this.saleOff_ID = sale_ID;
    }

    public void setService_ID(int service_ID) {
        this.service_ID = service_ID;
    }

    public void setStaff_ID(int staff_ID) {
        this.staff_ID = staff_ID;
    }
}

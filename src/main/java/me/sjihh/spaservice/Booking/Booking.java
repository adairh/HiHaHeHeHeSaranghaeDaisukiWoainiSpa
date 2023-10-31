package me.sjihh.spaservice.Booking;

import me.sjihh.spaservice.Room.Room;
import me.sjihh.spaservice.Service.Service;
import me.sjihh.spaservice.User.User;

import java.util.Date;

public class Booking {
    int id;
    Service service;
    Room room;
    User user;
    Date created;

    public Booking(int id, Service service, Room room, User user, Date created) {}
}

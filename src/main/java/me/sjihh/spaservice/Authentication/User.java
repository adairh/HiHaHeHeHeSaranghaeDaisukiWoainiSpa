package me.sjihh.spaservice.Authentication;

import me.sjihh.spaservice.Database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User{
    int id;
    String username;
    String address;
    String email;
    String phone;


    public User(int id, String username, String address, String email, String phone)
    {
        this.id = id;
        this.username = username;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }



    //================================================================


    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }






}

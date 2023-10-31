package me.sjihh.spaservice.User;

public class User {
    String name;
    UserDetail userDetails;

    public User(String name, UserDetail user) {}

    public User(String name) {}

    public UserDetail getUserDetails() {
        return userDetails;
    }
}

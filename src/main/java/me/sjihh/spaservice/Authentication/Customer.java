package me.sjihh.spaservice.Authentication;

public class Customer extends User{
    int level_id;
    public Customer(int id, int lvl, String username, String address, String email, String phone) {
        super(id, username, address, email, phone);
        level_id = lvl;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }
}

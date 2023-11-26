package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffLoader {
    private int staff_ID;
    private int service_ID;
    private String staff_name;

    public StaffLoader(int staff_ID, int service_ID, String staff_name) {
        this.staff_ID = staff_ID;
        this.service_ID = service_ID;
        this.staff_name = staff_name;
    }

    public static List<StaffLoader> loadStaffs() {
        List<StaffLoader> staffLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM staff";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int staff_ID = resultSet.getInt("staff_ID");
                    int service_ID = resultSet.getInt("service_ID");
                    String staff_name = resultSet.getString("staff_name");

                    StaffLoader staffLoader = new StaffLoader(staff_ID, service_ID, staff_name);
                    staffLoaders.add(staffLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffLoaders;
    }

    public int getStaff_ID() {
        return staff_ID;
    }

    public int getService_ID() {
        return service_ID;
    }

    public String getStaff_name() {
        return staff_name;
    }
}
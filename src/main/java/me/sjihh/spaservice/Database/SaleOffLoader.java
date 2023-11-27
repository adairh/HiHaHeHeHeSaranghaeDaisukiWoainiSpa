package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleOffLoader {
    private int saleOff_ID;
    private java.sql.Timestamp saleOff_start;
    private java.sql.Timestamp saleOff_finish;
    private int saleOff_percent;

    public SaleOffLoader(int saleOff_ID, java.sql.Timestamp saleOff_start,
                         java.sql.Timestamp saleOff_finish, int saleOff_percent) {
        this.saleOff_ID = saleOff_ID;
        this.saleOff_start = saleOff_start;
        this.saleOff_finish = saleOff_finish;
        this.saleOff_percent = saleOff_percent;
    }

    public static List<SaleOffLoader> loadSaleOffs() {
        List<SaleOffLoader> saleOffLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM saleoff";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int saleOff_ID = resultSet.getInt("saleOff_ID");
                    java.sql.Timestamp saleOff_start = resultSet.getTimestamp("saleOff_start");
                    java.sql.Timestamp saleOff_finish = resultSet.getTimestamp("saleOff_finish");
                    int saleOff_percent = resultSet.getInt("saleOff_percent");

                    SaleOffLoader saleOffLoader = new SaleOffLoader(saleOff_ID, saleOff_start,
                            saleOff_finish, saleOff_percent);
                    saleOffLoaders.add(saleOffLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return saleOffLoaders;
    }

    public int getSaleOff_ID() {
        return saleOff_ID;
    }

    public java.sql.Timestamp getSaleOff_start() {
        return saleOff_start;
    }

    public java.sql.Timestamp getSaleOff_finish() {
        return saleOff_finish;
    }

    public int getSaleOff_percent() {
        return saleOff_percent;
    }
}

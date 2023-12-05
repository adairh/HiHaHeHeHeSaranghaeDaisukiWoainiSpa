package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String database = "it_project";
    private static final String username = "root";
    private static final String password = "admin";
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}

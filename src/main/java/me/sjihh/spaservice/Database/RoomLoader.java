package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomLoader {
    private int room_id;
    private String room_type;
    private int room_price;

    public RoomLoader(int room_id, String room_type, int room_price) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.room_price = room_price;
    }

    public static List<RoomLoader> loadRooms() {
        List<RoomLoader> roomLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM room";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int room_id = resultSet.getInt("room_id");
                    String room_type = resultSet.getString("room_type");
                    int room_price = resultSet.getInt("room_price");

                    RoomLoader roomLoader = new RoomLoader(room_id, room_type, room_price);
                    roomLoaders.add(roomLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return roomLoaders;
    }

    public static RoomLoader getRoomById(int id) {
        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM room WHERE room_id = ? LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToRoom(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null; // Return null if no staff is found
    }

    private static RoomLoader mapResultSetToRoom(ResultSet resultSet) throws SQLException {
        int room_id = resultSet.getInt("room_id");
        String room_type = resultSet.getString("room_type");
        int room_price = resultSet.getInt("room_price");

        return new RoomLoader(room_id, room_type, room_price);
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public int getRoom_price() {
        return room_price;
    }
}

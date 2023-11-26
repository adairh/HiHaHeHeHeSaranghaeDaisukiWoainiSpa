package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private int level_ID;
    private String level_name;
    private int sale_percent;

    public LevelLoader(int level_ID, String level_name, int sale_percent) {
        this.level_ID = level_ID;
        this.level_name = level_name;
        this.sale_percent = sale_percent;
    }

    public static List<LevelLoader> loadLevels() {
        List<LevelLoader> levelLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM level";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int level_ID = resultSet.getInt("level_ID");
                    String level_name = resultSet.getString("level_name");
                    int sale_percent = resultSet.getInt("sale_percent");

                    LevelLoader levelLoader = new LevelLoader(level_ID, level_name, sale_percent);
                    levelLoaders.add(levelLoader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return levelLoaders;
    }

    public static LevelLoader getLevelByID(int levelID) {
        LevelLoader levelLoader = null;

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM level WHERE level_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, levelID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String level_name = resultSet.getString("level_name");
                        int sale_percent = resultSet.getInt("sale_percent");

                        levelLoader = new LevelLoader(levelID, level_name, sale_percent);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return levelLoader;
    }

    public int getLevel_ID() {
        return level_ID;
    }

    public String getLevel_name() {
        return level_name;
    }

    public int getSale_percent() {
        return sale_percent;
    }
}

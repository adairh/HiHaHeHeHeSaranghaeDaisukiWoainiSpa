package me.sjihh.spaservice.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreviewLoader {
    private int preview_ID;
    private int admin_ID;
    private int customer_ID;
    private int service_ID;
    private String comment;

    public PreviewLoader(int preview_ID, int admin_ID, int customer_ID, int service_ID, String comment) {
        this.preview_ID = preview_ID;
        this.admin_ID = admin_ID;
        this.customer_ID = customer_ID;
        this.service_ID = service_ID;
        this.comment = comment;
    }

    public static PreviewLoader getPreviewByID(int previewID) {
        PreviewLoader previewLoader = null;

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM preview WHERE preview_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, previewID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int admin_ID = resultSet.getInt("admin_ID");
                        int customer_ID = resultSet.getInt("customer_ID");
                        int service_ID = resultSet.getInt("service_ID");
                        String comment = resultSet.getString("comment");

                        previewLoader = new PreviewLoader(previewID, admin_ID, customer_ID, service_ID, comment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return previewLoader;
    }

    public static List<PreviewLoader> loadPreviews() {
        List<PreviewLoader> previewLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM preview";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int preview_ID = resultSet.getInt("preview_ID");
                    int admin_ID = resultSet.getInt("admin_ID");
                    int customer_ID = resultSet.getInt("customer_ID");
                    int service_ID = resultSet.getInt("service_ID");
                    String comment = resultSet.getString("comment");

                    PreviewLoader previewLoader = new PreviewLoader(preview_ID, admin_ID, customer_ID, service_ID, comment);
                    previewLoaders.add(previewLoader);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return previewLoaders;
    }

    public static List<PreviewLoader> getPreviewsByCustomerID(int customerID) {
        List<PreviewLoader> previewLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM preview WHERE customer_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, customerID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int preview_ID = resultSet.getInt("preview_ID");
                        int admin_ID = resultSet.getInt("admin_ID");
                        int service_ID = resultSet.getInt("service_ID");
                        String comment = resultSet.getString("comment");

                        PreviewLoader previewLoader = new PreviewLoader(preview_ID, admin_ID, customerID, service_ID, comment);
                        previewLoaders.add(previewLoader);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return previewLoaders;
    }

    public static List<PreviewLoader> getPreviewsByServiceID(int serviceID) {
        List<PreviewLoader> previewLoaders = new ArrayList<>();

        try (Connection connection = SQLConnection.getConnection()) {
            String query = "SELECT * FROM preview WHERE service_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, serviceID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int preview_ID = resultSet.getInt("preview_ID");
                        int admin_ID = resultSet.getInt("admin_ID");
                        int customer_ID = resultSet.getInt("customer_ID");
                        String comment = resultSet.getString("comment");

                        PreviewLoader previewLoader = new PreviewLoader(preview_ID, admin_ID, customer_ID, serviceID, comment);
                        previewLoaders.add(previewLoader);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return previewLoaders;
    }

    public int getPreview_ID() {
        return preview_ID;
    }

    public int getAdmin_ID() {
        return admin_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public int getService_ID() {
        return service_ID;
    }

    public String getComment() {
        return comment;
    }
}

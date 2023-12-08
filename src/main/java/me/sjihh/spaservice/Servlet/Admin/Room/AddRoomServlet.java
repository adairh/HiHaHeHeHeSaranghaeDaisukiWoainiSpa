package me.sjihh.spaservice.Servlet.Admin.Room;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/AddRoom")
public class AddRoomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get input parameters
        String roomType = request.getParameter("roomType");
        int roomPrice = Integer.parseInt(request.getParameter("roomPrice"));

        try {
            // Get DB connection
            Connection conn = SQLConnection.getConnection();

            // Create query
            String sql = "INSERT INTO room (room_type, room_price) VALUES (?, ?)";

            // Set parameters
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, roomType);
            statement.setInt(2, roomPrice);

            // Execute insert
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/rooms.jsp");
    }
}

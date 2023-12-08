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

@WebServlet("/admin/EditRoom")
public class EditRoomServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String roomType = request.getParameter("roomType");
        int roomPrice = Integer.parseInt(request.getParameter("roomPrice"));

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "UPDATE room SET room_type = ?, room_price = ? WHERE room_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, roomType);
            statement.setInt(2, roomPrice);
            statement.setInt(3, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/rooms.jsp");
    }
}

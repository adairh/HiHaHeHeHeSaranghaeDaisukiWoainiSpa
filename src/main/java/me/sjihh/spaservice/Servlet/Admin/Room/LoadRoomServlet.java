package me.sjihh.spaservice.Servlet.Admin.Room;

import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Database.RoomLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoadRoom")
public class LoadRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            RoomLoader room = RoomLoader.getRoomById(id);

            assert room != null;
            String json = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"roomType\": \"" + room.getRoom_type() + "\"," +
                    "\"roomPrice\": \"" + room.getRoom_price() + "\"" +
                    "}";

            out.println(json.replaceAll("\\r?\\n|\\r", ""));
        }

        response.sendRedirect("/admin/rooms.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        // Your code for handling POST requests goes here
    }
}

package me.sjihh.spaservice.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "getPrice", value = "/getPrice")
public class PriceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the room ID from the request parameter
        String roomIdStr = request.getParameter("roomId");

        // You should perform validation and error handling here
        // For simplicity, we assume the parameter is always valid in this example
        int roomId = Integer.parseInt(roomIdStr);

        // Assuming you have a method to get the price based on the room ID
        int price = getPriceForRoom(roomId) * 1000;

        request.getSession().setAttribute("room", roomId);

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            out.println("{\"price\": " + price + "}");
        }
    }

    // Replace this method with your actual logic to get the price from the database or elsewhere
    private int getPriceForRoom(int roomId) {
        // This is a placeholder. You should replace it with your actual logic.
        // For simplicity, just returning a hardcoded price based on room ID.
        switch (roomId) {
            case 1:
                return 100;
            case 2:
                return 80;
            case 3:
                return 120;
            case 4:
                return 100;
            case 5:
                return 80;
            case 6:
                return 120;
            case 7:
                return 100;
            case 8:
                return 80;
            case 9:
                return 120;
            case 10:
                return 100;
            // Add more cases as needed
            default:
                return 0; // Handle unknown room IDs appropriately
        }
    }
}

package me.sjihh.spaservice.Servlet.Admin.Review;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/EditPreview")
public class EditPreviewServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        int id = Integer.parseInt(request.getParameter("id"));
        int adminID = 1;
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        String comment = request.getParameter("comment");

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "UPDATE preview SET admin_ID = ?, customer_ID = ?, service_ID = ?, comment = ? WHERE preview_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, adminID);
            statement.setInt(2, customerID);
            statement.setInt(3, serviceID);
            statement.setString(4, comment);
            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/preview.jsp");
    }
}

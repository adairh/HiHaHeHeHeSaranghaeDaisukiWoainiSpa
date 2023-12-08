package me.sjihh.spaservice.Servlet.Admin.Customer;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/admin/EditCustomer")
public class EditCustomerServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        int id = Integer.parseInt(request.getParameter("id"));
        int levelID = Integer.parseInt(request.getParameter("levelID"));
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "UPDATE customer SET level_ID = ?, username = ?, address = ?, telephone = ?, email = ? WHERE customer_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, levelID);
            statement.setString(2, username);
            statement.setString(3, address);
            statement.setString(4, telephone);
            statement.setString(5, email);
            statement.setInt(6, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/customer.jsp");
    }
}

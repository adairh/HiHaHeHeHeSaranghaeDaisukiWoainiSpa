package me.sjihh.spaservice.Servlet.Admin;

import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/DeleteSaleOff")
public class DeleteSaleOffServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "DELETE FROM saleoff WHERE saleOff_ID = ?";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/saleoff.jsp");
    }
}
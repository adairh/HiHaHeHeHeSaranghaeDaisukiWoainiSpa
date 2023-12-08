package me.sjihh.spaservice.Servlet.Admin.SaleOff;

import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Database.SaleOffLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/AddSaleOff")
public class AddSaleOffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get input parameters 
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        int percent = Integer.parseInt(request.getParameter("percent"));
        String code = request.getParameter("code");

        if (!SaleOffLoader.alreadyHave(code, -1)) {
            try {
                // Get DB connection
                Connection conn = SQLConnection.getConnection();

                // Create query
                String sql = "INSERT INTO saleoff (saleOff_start, saleOff_finish, saleOff_percent, saleOff_code) VALUES (?, ?, ?, ?)";

                // Set parameters
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, start);
                statement.setString(2, end);
                statement.setInt(3, percent);
                statement.setString(4, code);

                // Execute insert
                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/admin/saleoff.jsp");
    }

}
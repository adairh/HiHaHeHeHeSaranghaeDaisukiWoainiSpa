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

@WebServlet("/EditSaleOff")
public class EditSaleOffServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Get parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String start = request.getParameter("start"); 
        String end = request.getParameter("end");
        int percent = Integer.parseInt(request.getParameter("percent"));
        String code = request.getParameter("code");

        try {
            Connection conn = SQLConnection.getConnection();
            String sql = "UPDATE saleoff SET saleOff_start = ?, saleOff_finish = ?, saleOff_percent = ?, saleOff_code = ? WHERE saleOff_ID = ?";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, start); 
            statement.setString(2, end);
            statement.setInt(3, percent);
            statement.setString(4, code);
            statement.setInt(5, id);
            
            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package me.sjihh.spaservice.Servlet.Admin.SaleOff;

import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Database.SaleOffLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/LoadSaleOff")
public class LoadSaleOffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(Integer.parseInt(request.getParameter("id")));
        System.out.println(request.getParameterMap().size());
        int id = Integer.parseInt(request.getParameter("id"));

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            SaleOffLoader saleOff = SaleOffLoader.getSaleOffById(id);

            assert saleOff != null;
            String json = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"code\": \"" + saleOff.getSaleOff_code() + "\"," +
                    "\"start\": \"" + saleOff.getSaleOff_start().toString() + "\"," +
                    "\"finish\": \"" + saleOff.getSaleOff_finish().toString() + "\"," +
                    "\"percent\": \"" + saleOff.getSaleOff_percent() + "\"" +
                    "}";

            out.println(json);
        }

        response.sendRedirect("/admin/saleoff.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Get parameters

    }
}
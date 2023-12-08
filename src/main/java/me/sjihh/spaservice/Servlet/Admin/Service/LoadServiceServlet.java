package me.sjihh.spaservice.Servlet.Admin.Service;

import me.sjihh.spaservice.Database.SQLConnection;
import me.sjihh.spaservice.Database.ServiceLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoadService")
public class LoadServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            ServiceLoader service = ServiceLoader.getServiceById(id);

            assert service != null;
            String json = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"serviceName\": \"" + service.getService_name() + "\"," +
                    "\"servicePrice\": \"" + service.getService_price() + "\"," +
                    "\"serviceTime\": \"" + service.getService_time() + "\"," +
                    "\"serviceDetail\": \"" + service.getService_detail() + "\"" +
                    "}";

            out.println(json.replaceAll("\\r?\\n|\\r", ""));
        }

        response.sendRedirect("/admin/services.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        // Your code for handling POST requests goes here
    }
}

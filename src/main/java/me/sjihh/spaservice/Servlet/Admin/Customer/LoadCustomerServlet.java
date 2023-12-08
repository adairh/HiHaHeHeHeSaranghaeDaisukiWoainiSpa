package me.sjihh.spaservice.Servlet.Admin.Customer;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoadCustomer")
public class LoadCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            Customer customer = Customer.getUserById(id);

            assert customer != null;
            String json = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"levelID\": \"" + customer.getLevel_id() + "\"," +
                    "\"username\": \"" + customer.getUsername() + "\"," +
                    "\"address\": \"" + customer.getAddress() + "\"," +
                    "\"telephone\": \"" + customer.getPhone() + "\"," +
                    "\"email\": \"" + customer.getEmail() + "\"" +
                    "}";

            out.println(json.replaceAll("\\r?\\n|\\r", ""));
        }

        response.sendRedirect("/admin/customer.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        // Your code for handling POST requests goes here
    }
}

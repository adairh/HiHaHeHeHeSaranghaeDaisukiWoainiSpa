package me.sjihh.spaservice.Servlet.Admin.Review;

import me.sjihh.spaservice.Database.PreviewLoader;
import me.sjihh.spaservice.Database.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoadReview")
public class LoadReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Construct a simple JSON response
            PreviewLoader review = PreviewLoader.getPreviewByID(id);

            assert review != null;
            String json = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"adminID\": \"" + review.getAdmin_ID() + "\"," +
                    "\"customerID\": \"" + review.getCustomer_ID() + "\"," +
                    "\"serviceID\": \"" + review.getService_ID() + "\"," +
                    "\"comment\": \"" + review.getComment() + "\"" +
                    "}";

            out.println(json);
        }

        response.sendRedirect("/admin/review.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        // Your code for handling POST requests goes here
    }
}

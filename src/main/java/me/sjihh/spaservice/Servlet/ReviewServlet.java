package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Database.PreviewLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Review", value = "/Review")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = ((Customer)request.getSession().getAttribute("user")).getId();
        int serviceId = Integer.parseInt(request.getParameter("service_ID"));
        String comment = request.getParameter("comment");

        PreviewLoader preview = PreviewLoader.getPreviewLoaderByUserAndService(customerId, serviceId);

        if (comment == null) comment = preview.getComment();

        PreviewLoader.insertOrUpdateReview(customerId, serviceId, comment);

        getServletContext()
                .getRequestDispatcher("/rooms.jsp")
                .forward(request, response);
    }
}

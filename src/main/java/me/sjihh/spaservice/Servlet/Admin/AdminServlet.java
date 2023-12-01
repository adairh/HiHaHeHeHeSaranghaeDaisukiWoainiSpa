package me.sjihh.spaservice.Servlet.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/admin/*", value = "/admin/*")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null || !(boolean)request.getSession().getAttribute("admin")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/sign.jsp");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            dispatcher.forward(request, response);
            return;
        }
        String pathInfo = request.getPathInfo();

        // Check if the pathInfo is not null to avoid infinite loop
        if (pathInfo != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPanel" + pathInfo);
            dispatcher.forward(request, response);
        } else {
            // If pathInfo is null, handle it accordingly (maybe send a 404 or redirect to a default page)
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
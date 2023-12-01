package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Authentication.Admin;
import me.sjihh.spaservice.Authentication.AuthenticateHandler;
import me.sjihh.spaservice.Authentication.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO POST LOGIN");
        String email = request.getParameter("log-email");
        String password = request.getParameter("log-password");

        User authenticatedUser = new AuthenticateHandler().login(email, password);

        if (authenticatedUser instanceof Admin) {
            request.getSession().setAttribute("admin", true);
        }

        if (authenticatedUser != null) {
            request.getSession().setAttribute("user", authenticatedUser);
            if (!(authenticatedUser instanceof Admin)) response.sendRedirect("profile.jsp");
            else response.sendRedirect("admin/index.jsp");
        } else {
            request.setAttribute("errorMessage", "Authentication failed. Please try again.");
            request.getRequestDispatcher("sign.jsp").forward(request, response);
        }
    }
}

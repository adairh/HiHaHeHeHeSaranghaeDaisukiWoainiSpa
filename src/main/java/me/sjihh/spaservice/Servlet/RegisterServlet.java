package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Authentication.AuthenticateHandler;
import me.sjihh.spaservice.Authentication.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO POST REG");
        String user = request.getParameter("reg-user");
        String email = request.getParameter("reg-email");
        String password = request.getParameter("reg-password");

        try {
            new AuthenticateHandler().register(user, password, email);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("profile.jsp");
        /*if (authenticatedUser != null) {
            request.getSession().setAttribute("authenticatedUser", authenticatedUser);
            response.sendRedirect("profile.jsp");
        } else {
            request.setAttribute("errorMessage", "Authentication failed. Please try again.");
            request.getRequestDispatcher("sign.jsp").forward(request, response);
        }*/
    }
}

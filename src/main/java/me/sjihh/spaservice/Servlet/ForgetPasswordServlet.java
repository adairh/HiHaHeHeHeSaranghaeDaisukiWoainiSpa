package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Email.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forgetPassword")
public class ForgetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user's email from the request
        String userEmail = request.getParameter("email");

        // Check if userEmail exists in the database and retrieve user details

        // Generate a new password
        String newPassword = generateNewPassword();

        // Update user's password in the database

        // Send an email with the new password
        EmailSender.sendForgetPasswordEmail(userEmail, newPassword);

        // Forward or redirect as needed
    }

    private String generateNewPassword() {
        // Logic to generate a new password
        return "newPassword123";
    }
}

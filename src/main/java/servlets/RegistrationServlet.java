package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBUtil;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            // Get a connection to the database
            Connection connection = DBUtil.getConnection();
            
            // Create a prepared statement with the INSERT query
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            // Set the parameters for the username and password
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            // Execute the query to insert the user data into the database
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully.");
            }
            
            // Close the prepared statement and database connection
            preparedStatement.close();
            connection.close();
            
            // After successful registration, you might redirect the user to the login page
            response.sendRedirect("login.html");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors or registration failures
            response.sendRedirect("registration_error.html");
        }
    }
}

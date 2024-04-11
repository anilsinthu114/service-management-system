package servlets;

import model.Service;
import model.ServiceDTO;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewServiceServlet")
public class ViewServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve services from the database using a DAO class
            List<Service> services = ServiceDTO.getAllServices();

            // Set services as an attribute in the request
            request.setAttribute("services", services);

            // Forward the request to a JSP page to display the services
            request.getRequestDispatcher("/viewService.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions and forward to an error page
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

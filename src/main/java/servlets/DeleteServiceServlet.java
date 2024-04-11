package servlets;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ServiceDTO;

@WebServlet("/DeleteServiceServlet")
public class DeleteServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve service ID from request parameter
        int serviceId = Integer.parseInt(request.getParameter("service_id"));

        // Create an instance of ServiceDAO
        ServiceDTO serviceDAO = new ServiceDTO();

        // Delete the service
		boolean success = serviceDAO.deleteService(serviceId);

		if (success) {
		    // Redirect to view service page upon successful deletion
		    response.sendRedirect(request.getContextPath() + "/ViewServiceServlet");
		} else {
		    // Handle case where no rows were deleted
		    response.getWriter().println("No service found with the given ID.");
		}
    }
}

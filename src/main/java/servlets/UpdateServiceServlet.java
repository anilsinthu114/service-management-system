package servlets;
import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Service;
import model.ServiceDTO;


@WebServlet("/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from request
        int serviceId = Integer.parseInt(request.getParameter("service_id"));
       

        try {
            // Create an instance of ServiceDAO
            ServiceDTO serviceDAO = new ServiceDTO();

            // Check if service with the given ID exists
            Service service = serviceDAO.getServiceById(serviceId);
            if (service == null) {
                // If service does not exist, forward to error page
                request.setAttribute("errorMessage", "Error: Service with ID " + serviceId + " does not exist.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;                        
            }
            
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            BigDecimal price = null;
            String priceParam = request.getParameter("price");
            if (priceParam != null && !priceParam.isEmpty()) {
                price = new BigDecimal(priceParam);
            }
            // Update service details
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);

            // Update service in the database
            serviceDAO.updateService(service);

            // Redirect to a success page or display a success message
            response.sendRedirect("/service-management-system/ViewServiceServlet");
        } catch (NumberFormatException e) {
            // Handle number format exception
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: Invalid input format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

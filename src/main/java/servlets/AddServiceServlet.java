package servlets;



import model.ServiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



// Ensure to import necessary database connectivity libraries

@WebServlet("/AddServiceServlet")
public class AddServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price")); // Convert price to BigDecimal
        
        // Assuming you have a ServiceDAO class to handle database operations
        ServiceDTO serviceDAO = new ServiceDTO();
        
        // Add the service to the database
        boolean success = serviceDAO.addService(name, description, price);
        
        // Prepare response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Display success or failure message
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Service</title>");
        out.println("</head>");
        out.println("<body>");
        if(success) {
            out.println("<h2>Service added successfully!</h2>");
            out.println("<script>");
            out.println("setTimeout(function() { window.location.href = 'addService.jsp'; }, 5000);");
            out.println("</script>");
        } else {
            out.println("<h2>Failed to add service. Please try again.</h2>");
        }
        out.println("</body>");
        out.println("</html>");
    }

}


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Service" %>
<%@ page import="java.math.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Service</title>
</head>
<body>
    <h2>Edit Service</h2>
    
    <form id="updateServiceForm" action="UpdateServiceServlet" method="post">
        <%-- Retrieve Service object from request attribute --%>
        <% Service service = (Service) request.getAttribute("service"); %>
        
        <%-- Check if service object is not null before accessing its properties --%>
        <% if (service != null) { %>
            <%-- Extract service properties --%>
            <% int serviceId = service.getService_id(); %>
            <% String name = service.getName(); %>
            <% String description = service.getDescription(); %>
            <% BigDecimal price = service.getPrice(); %>
            <%-- Convert BigDecimal price to String for display --%>
            <% String priceString = price != null ? price.toString() : ""; %>
            
            <%-- Set hidden input field for service ID --%>
            <input type="hidden" name="service_id" value="<%= serviceId %>" />
            
            <%-- Input fields for editing service details --%>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= name %>" /><br>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="<%= description %>" /><br>
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="<%= priceString %>" /><br>
            
            <%-- Submit button --%>
            <input type="submit" value="Submit">
        <% } else { %>
            <p>No service found for editing.</p>
        <% } %>
    </form>
</body>
</html>

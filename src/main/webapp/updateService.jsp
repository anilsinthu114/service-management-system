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
    <div class="container">
        <h2>Update Service</h2>
        <form id="updateServiceForm" onsubmit="submitForm(event)">
            <label for="service_id">Enter Service ID:</label>
            <input type="text" id="service_id" name="service_id" placeholder="Enter Service ID:">
    
            <label for="name">Enter New Service Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter New Service Name:">
    
            <label for="description">Enter New Service Description:</label>
            <input type="text" id="description" name="description" placeholder="Enter New Service Description:">
    
            <label for="price">Enter New Service Price:</label>
            <input type="text" id="price" name="price" placeholder="Enter New Service Price:">
    
            <input type="submit" value="Update Service">
        </form>
    </div>

    <script>
        function submitForm(event) {
            event.preventDefault(); // Prevent default form submission
            var serviceId = document.getElementById('service_id').value;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/service-management-system/GetServiceServlet', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        var service = JSON.parse(xhr.responseText);
                        if (service) {
                            document.getElementById('name').value = service.name;
                            document.getElementById('description').value = service.description;
                            document.getElementById('price').value = service.price;
                        } else {
                            alert('Service not found');
                        }
                    } else {
                        alert('Error occurred while fetching service data');
                    }
                }
            };
            xhr.send('service_id=' + encodeURIComponent(serviceId));
        }
    </script>
</body>
</html>

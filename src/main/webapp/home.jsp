<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*" %>
<%
    // Check if the user is logged in
    HttpSession sessions = request.getSession(false);
    if (sessions == null || sessions.getAttribute("username") == null) {
        // If not logged in, redirect to the login page
        response.sendRedirect("login.html"); // Change "login.jsp" to your actual login page
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Service Management System</title>
    <!-- Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            text-align: center;
            margin-top: 10px;
            color: #007bff; /* Blue color for h1 */
        }
        /* Navbar styling */
        .navbar {
            background-color: #f3f6f4; /* Dark gray background */
        }
        .navbar-brand {
            color: #ffffff; /* White text color */
        }
        .navbar-brand img {
            width: 30px; /* Adjust the width of the user avatar */
            height: auto;
            margin-right: 10px;
            border-radius: 50%; /* Make the avatar rounded */
            cursor: pointer; /* Change cursor to pointer */
        }
        .navbar-nav .nav-link {
            color: #ffffff; /* White text color */
            margin-right: 10px; /* Adjust spacing between menu items */
        }
        .navbar-nav .nav-link:hover {
            color: blue; /* Light text color on hover */
        }
        /* End Navbar styling */
        .user-info {
            position: absolute;
            top: 60px;
            right: 10px;
            display: none; /* Initially hide user info */
            background-color: #ffffff; /* White background */
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Shadow effect */
            z-index: 1000; /* Ensure the info appears on top of other elements */
        }
        .user-info img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <h1>Service Management System</h1>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#" onclick="toggleUserInfo()">
                <img src="images/user-avatar.jpg" alt="User Avatar">
                <%= request.getSession().getAttribute("username") %>
            </a>
            <div id="userInfo" class="user-info">
                <!-- User details -->
                <img src="images/user-avatar.jpg" alt="User Avatar">
                <span><%= request.getSession().getAttribute("username") %></span>
                <!-- Logout button -->
                <form action="LogoutServlet" method="post">
                    <button type="submit" class="btn btn-danger btn-sm mt-2">Logout</button>
                </form>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="addServiceForm.html">Add </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/service-management-system/ViewServiceServlet">View</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="updateServiceForm.html">Update </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="deleteService.jsp">Delete</a>
                    </li>
                </ul>
                <form action="LogoutServlet" method="post">
                    <button type="submit" class="btn btn-danger btn-sm mt-2">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <!-- Bootstrap JS script -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script>
        function toggleUserInfo() {
            var userInfo = document.getElementById('userInfo');
            if (userInfo.style.display === 'none') {
                userInfo.style.display = 'block';
            } else {
                userInfo.style.display = 'none';
            }
        }
    </script>
</body>
</html>

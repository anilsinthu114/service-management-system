<%@include file="home.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INFO</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
/* Additional styling */
body {
	background-color: #f8f9fa; /* Set background color */
}

.container {
	margin-top: 50px; /* Add some top margin */
}
</style>
</head>
<body>
	<div class="container">


		<%-- Error Message --%>
		<%
		if (request.getAttribute("errorMessage") != null) {
		%>
		<h1 class="mt-4">Error</h1>
		<div class="alert alert-danger mt-3" role="alert">
			<%=request.getAttribute("errorMessage")%>
		</div>
		<div class="my-3">Please select a Membership ID:</div>
		<div>
			<a href="updateServiceForm.html" class="btn btn-primary">Select
				Membership ID</a>
		</div>
		<%
		}
		%>

		<%-- Success Message --%>
		<%
		if (request.getAttribute("successMessage") != null) {
		%>
		<h1 class="alert mt-3">SUCESS</h1>
		<div class="alert alert-success mt-3" role="alert">
			<%=request.getAttribute("successMessage")%>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>

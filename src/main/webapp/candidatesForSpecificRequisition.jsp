<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ page import="com.sf.model.User"%>
 <%@page import="java.util.*" %>
<%
	if (session.getAttribute("user") == null)
		response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<script type="text/javascript" src = "js/test.js" ></script>
	<title>Candidates Applied</title>
</head>
<body>
	<jsp:include page="_header2.jsp"></jsp:include>
	
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<table class="table">
				<tr>
					<th>#</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email ID</th>
					<th>Gender</th>
					<th>DOB</th>
				</tr>
				<%
					ArrayList<User> userList = null;
					if (request.getAttribute("userList") instanceof List) {
						userList = (ArrayList<User>) request.getAttribute("userList");
					}
					if (userList != null) {
						int count = 0;
						for (User user : userList) {
							count++;
				%>
				<tr>
					<td><%=count %></td>
					<td><%=user.getFirstName()%></td>
					<td><%=user.getLastName()%></td>
					<td><%=user.getEmailId()%></td>
					<td><%=user.getGender() %>
					<td><%=user.getDob() %></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
		<div class="col-1"></div>
	</div>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
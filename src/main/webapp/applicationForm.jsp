<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ page import="com.sf.model.User"%>

<%
	if (session.getAttribute("user") == null){
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<script type="text/javascript" src = "js/test.js" ></script>
	<title>Apply Here</title>
</head>
<body>
	<jsp:include page="_header2.jsp"></jsp:include>
	
	<div class = "row">
		<div class="col-4"></div>
		<div class="col-5">
			<form action="ApplicationCreator" method ="POST" enctype='multipart/form-data'>
			<input type="hidden" name="reqId" value=<%=request.getParameter("reqId")%>>
				<div class="row form-group">
					<div class="col-3">
						<label for="resume" >Resume</label>
					</div>
					<div class="col-6">
						<input class="form-input-element" type="file" id="resume" name="resume"></input>
					</div>
					<div class="col-3"></div>
				</div>
				
				<div class="row form-group">
					<div class="col-3">
						<label for="coverLetter" >Cover Letter</label>
					</div>
					<div class="col-6">
						<input class="form-input-element" type="file" id="coverLetter" name="coverLetter"></input>
					</div>
					<div class="col-3"></div>
				</div>
				<div class="row form-group">
					<div class="col-3"></div>
					<div class="col-6"></div>
					<div class="col-3">
					<input class="button button-info" type="submit" value="apply"></div>
				</div>
			</form>
		</div>
		<div class="col-3"></div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
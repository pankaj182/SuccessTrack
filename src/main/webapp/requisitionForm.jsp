<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html">
<html>
<%
	if(session.getAttribute("user") == null)
	   response.sendRedirect("index.jsp");
%>
<head>
	<title>create a Job! </title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<script type="text/javascript" src="js/check.js"></script>
</head>
<body>
<jsp:include page="_header2.jsp"></jsp:include>
	
<div class = "row">
<form class="form-horizontal" name="requisitionForm" action="RequisitionCreator" method="POST"  autocomplete="on">
	<div class="col-1"></div>
	<div class="col-5">
		
			<div class="row form-group">
				<div class="col-3">
					<label for="jobTitle">Job Title*</label>
				</div>
				<div class="col-6">
					<input class="form-input-element " type="text" name="jobTitle" id="jobTitle" onblur="validate('requisitionForm','text','jobTitle','jobTitle_error');"></input>
				</div>
				<div class="col-3" id="jobTitle_error"></div>
			</div>
			<div class="row form-group">
				<div class="col-3">
					<label for="jobDescription">Job Description</label>
				</div>
				<div class="col-6">
					<textarea  name="jobDescription" id="jobDescription" rows="4" cols="40"></textarea>
				</div>
				<div class="col-3"></div>
			</div>
			<div class="row form-group">
				<div class="col-3">
					<label for="originator">Originator*</label>
				</div>
				<div class="col-6">
					<input class="form-input-element " type="text" name="originator" id="originator"></input>
				</div>
				<div class="col-3"></div>
			</div>
			<div class="row form-group">
				<div class="col-3">
					<label for="ctc">CTC</label>
				</div>
				<div class="col-6">
					<input class="form-input-element " type="text" name="ctc" id="ctc"></input>
				</div>
				<div class="col-3"></div>
			</div>
	</div>
	<div class = "col-5">
		<div class="row form-group">
					<div class="col-3">
						<label for = "startDate">Active From*</label>
					</div>
					<div class="col-6">
						<input class="form-input-element " type = "Date" name = "startDate" id = "startDate" ></input>
					</div>
					<div class="col-3"></div>
				</div>
				<div class="row form-group">
					<div class="col-3">
						<label for = "endDate">Active Till*</label>
					</div>
					<div class="col-6">
						<input class=" form-input-element " type = "Date" name = "endDate" id = "endDate"></input>
					</div>
					<div class="col-3"></div>
				</div>
				<div class="row form-group">
					<div class="col-3">
						<label for=requiredSkills >Required Skills*</label>
					</div>
					<div class="col-6">
						<textarea  name="requiredSkills" id="requiredSkills" rows="5" cols="40"></textarea>
					</div>
					<div class="col-3"></div>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-6"></div>
				<div class="col-3">
					<input class="button button-info" type="submit" value="create">
				</div>
				<div class="col-3"></div>
			</div>
			
			<div class="col-1"></div>
			
		</form>
</div>
							
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
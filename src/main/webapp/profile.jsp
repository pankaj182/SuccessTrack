<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
	pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sf.model.User"%>
<%@page import="com.sf.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
 <%
	if (session.getAttribute("user") == null)
		response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<title>Profile Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<script type="text/javascript" src="js/check.js"></script>
</head>
<body>
	<jsp:include page="_header2.jsp"></jsp:include>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<div class="col-5">
				<table class="table">
				<%	User userPersonal = null;
					if(request.getAttribute("userPersonal") instanceof User)
						userPersonal = (User)request.getAttribute("userPersonal");
				%>
						<tr><th>Personal Information</th></tr>
						<tr>
							<td>
							<%if(userPersonal.getProfileImage()==null){%><img  src="images/profile.png" alt="profile image not available" width="50%" height="50%" >
							<%}else {%>
								<img alt="profile Picture" src="data:image/jpeg;base64,<%=userPersonal.getBase64encodedDP() %>" height="350px" width="100%"/>
							<%} %>
							</td>
						</tr>
						<tr>
							<td>
								<form class="form-horizontal" action="ProfilePictureUpdate" method="POST" enctype='multipart/form-data'>
									<input type="file" name="dp" ></input>
									<input type="submit" value = "update" ></input>
								</form>
							</td>
						</tr>
						<tr><td><%=userPersonal.getFirstName()+" "+userPersonal.getLastName() %></td></tr>
						<tr><td><%=userPersonal.getEmailId() %></td></tr>
						<tr><td><%=userPersonal.getGender() +" "+userPersonal.getDob()%></td></tr>
						<tr><td><%=userPersonal.getCity() +" "+userPersonal.getState()+" "+userPersonal.getCountry() %></td></tr>
				</table>
				</div>
			<div class="col-2"></div>
			<div class="col-5">
				<table class="table">
			<%	ArrayList<CandidateBackground> list = null;
				if(request.getAttribute("userProfile") instanceof List)
					list = (ArrayList<CandidateBackground>)request.getAttribute("userProfile");
			%>
			<%
				for(CandidateBackground cb : list){
					String infoType = cb.getInfoType();
					if(infoType.equalsIgnoreCase("education")){			
			%>			
						<tr><th>Education</th></tr>
						<tr><td><%=cb.getInstituteName() %></td></tr>
						<tr><td><%=cb.getDegree() %></td></tr>
						<tr><td><%=cb.getMajor() %></td></tr>
						<tr><td><%=cb.getCgpa()+"  "+cb.getPercentage()+"%" %></td></tr>
						<tr><td><%=cb.getCity() +" "+cb.getState()+" "+cb.getCountry() %></td></tr>
						<tr><td><%=cb.getStartDate()+" - "+cb.getEndDate() %></td></tr>
				<%} else if(infoType.equalsIgnoreCase("experience")){ %>
						<tr><th>Experience</th></tr>
						<tr><td><%=cb.getCompanyName() %></td></tr>
						<tr><td><%=cb.getJobTitle() %></td></tr>
						<tr><td><%=cb.getSalary() %></td></tr>
						<tr><td><%=cb.getCity() +" "+cb.getState()+" "+cb.getCountry() %></td></tr>
						<tr><td><%=cb.getStartDate()+" - "+cb.getEndDate() %></td></tr>
				<%}else{ %>
					<tr><th>Skills</th></tr>
					<tr><td><%=cb.getSkillSet() %></td></tr>
			<%} %>
			<%} %>
			</table>
			</div>
			<% request.removeAttribute("userProfile");
				request.removeAttribute("userPersonal");
			%>
		<div class="row">
			<div class="col-5">
				<form class="form-horizontal" name="educationInfo" action="UserProfile" method="POST" autocomplete="on">
					<fieldset>
						<legend>Education</legend>
						<input type="hidden" name="formName" value="educationInfo" />
						<div class="row form-group">
							<label class=" col-3" for="instituteName">Institute*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="instituteName"
									name="instituteName" placeholder="Institute" onblur="validate('educationInfo','text','instituteName','instituteName_error');"></input>
							</div>
							<div class="col-3" id="instituteName_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="degree">Degree*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="degree"
									name="degree" placeholder="degree" onblur="validate('educationInfo','text','degree','degree_error');"></input>
							</div>
							<div class="col-3" id="degree_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="major">Major*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="major"
									name="major" placeholder="Major" required></input>
							</div>
							<div class="col-3" id="major_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="cgpa">CGPA*</label>
							<div class="col-6">
								<input class="form-input-element" type="number" step="0.01"
									min=0 max=10 id="cgpa" name="cgpa" placeholder="cgpa"
									required></input>
							</div>
							<div class="col-3" id="cgpa_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="percentage">Percentage*</label>
							<div class="col-6">
								<input class="form-input-element" type="number" step="0.01"
									min="0.00" max="100.00" id="percentage" name="percentage"
									placeholder="Percentage" required></input>
							</div>
							<div class="col-3" id="percentage_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="startDate">Start Date*</label>
							<div class="col-6">
								<input type="Date" id="startDate" name="startDate" required></input>
							</div>
							<div class="col-3" id="startDate_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="endDate">End Date*</label>
							<div class="col-6">
								<input type="Date" id="endDate" name="endDate" required></input>
							</div>
							<div class="col-3" id="endDate_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="eduCity">City</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="eduCity"
									name="eduCity" required></input>
							</div>
							<div class="col-3" id="eduCity_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="eduState">State*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="eduState"
									name="eduState" required></input>
							</div>
							<div class="col-3" id="eduState_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="eduCountry">Country*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="eduCountry"
									name="eduCountry" required></input>
							</div>
							<div class="col-3" id="eduCountry_error"></div>
						</div>
						<div class="col-8"></div>
						<div class="col-3">
							<input type="submit" class="button button-info" value="Save">
						</div>
						<div class="col-1"></div>
					</fieldset>
					<br>
				</form>
			<form class="form-horizontal" name="skillsInfo" action="UserProfile" method="POST" autocomplete="on">
				<fieldset>
					<legend>Skills</legend>
					<input type="hidden" name="formName" value="skillsInfo" />
					<div class="row form-group">
						<label class=" col-3" for="skills">Skills</label>
						<div class="col-6">
							<textarea id="skills" name="skills" rows = "5"></textarea>
						</div>
						<div class="col-3" id="skills_error"></div>
					</div>
					<div class="col-8"></div>
					<div class="col-3">
						<input type="submit" class="button button-info" value="Save">
					</div>
					<div class="col-1"></div>
				</fieldset>
			</form>
			</div>
			
			<!--  		---------------------------------------------------------------------- 			-->
			<div class="col-2"></div>
			<div class="col-5">
				<form class="form-horizontal" name="experienceInfo" action="UserProfile" method="POST" autocomplete="on">
					<fieldset>
						<legend>Experience</legend>
						<input type="hidden" name="formName" value="experienceInfo" />
						<div class="row form-group">
							<label class=" col-3" for="jobTitle">Job Title*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="jobTitle" name="jobTitle" placeholder="Your designation"></input>
							</div>
							<div class="col-3" id="jobTitle_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="companyName">Company Name*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="companyName" name="companyName" placeholder="company Name" required></input>
							</div>
							<div class="col-3" id="companyName_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="startDate">Start Date*</label>
							<div class="col-6">
								<input type="date" id="startDate" name="startDate" required></input>
							</div>
							<div class="col-3" id="cgpa_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="endDate">End Date*</label>
							<div class="col-6">
								<input type="date" id="endDate" name="endDate" required></input>
							</div>
							<div class="col-3" id="endDate_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="jobCity">Job City</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="jobCity"
									name="jobCity" required></input>
							</div>
							<div class="col-3" id="jobCity_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="jobState">Job State*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="jobState"
									name="jobState" required></input>
							</div>
							<div class="col-3" id="jobState_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="jobCountry">Job Country*</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="jobCountry"
									name="jobCountry" required></input>
							</div>
							<div class="col-3" id="jobCountry_error"></div>
						</div>
						<div class="row form-group">
							<label class=" col-3" for="currentSalary">Current Salary</label>
							<div class="col-6">
								<input class="form-input-element" type="text" id="currentSalary"
									name="currentSalary"></input>
							</div>
							<div class="col-3" id="currentSalary_error"></div>
						</div>
	
						<div class="col-8"></div>
						<div class="col-3">
							<input type="submit" class="button button-info" value="Save">
						</div>
						<div class="col-1"></div>
						<br>
					</fieldset>
					<br>
				</form>
			</div>
			</div>
			<br><br>
		</div>
		<div class="col-1"></div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
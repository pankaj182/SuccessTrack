<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript" src = "js/test.js" ></script>
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	
	<title>signup here</title>
	<script>
			function signupCall(){
			var xmlHttp = new XMLHttpRequest();
			var emailid = document.getElementById('emailid').value;
			var password = document.getElementById('passwd2').value;
			var fname = document.getElementById('fname').value;
			var lname = document.getElementById('lname').value;
			var gender = "F";
			var dob = document.getElementById('dob').value;
			if(document.getElementById('male').checked)
				gender = "M";
			var url = "Signup";
			var data = "emailid="+emailid+"&passwd2="+password+"&fname="+fname+"&lname="+lname+"&gender="+gender+"&dob="+dob;
			
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange=function()
			  {
			      if (xmlHttp.readyState==4 && xmlHttp.status==200){
			    	  if(xmlHttp.responseText.includes("success")){
			    		  document.getElementById('status').style.color  = "green";
			    		  document.getElementById('status').innerHTML = "Successfully Registered,Login to Continue";
			    	  }
			    	  else{
			    		  document.getElementById('status').style.color  = "red";
			    		  document.getElementById('status').innerHTML = xmlHttp.responseText;
			    	  }
			      }
			  }
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(data);
		}
	</script>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
	<div class = "row">
		<div class="col-6">
		</div>
		<div class="col-5">
			<form class="form-horizontal" name="signupForm" action="Signup" method="POST"  onsubmit="return validateSignup()" autocomplete="on">
					
				<fieldset>
						<legend>Account Information</legend>
						
						<div class="row form-group">
							<div class="col-3">
								<label for="emailid">Email ID*</label>
							</div>
							<div class="col-6">
								<input class="form-input-element" type="email" id="emailid" name="emailid" required></input>
							</div>
							<div class="col-3" id="email_error"></div>
						</div>
						<div style="color:red;">${error_message} </div>
						<% request.removeAttribute("error_message"); %>
						
						<div class="row form-group">
							<div class="col-3">
								<label for="passwd">Password*</label>
							</div>
							<div class="col-6">
								<input class="form-input-element" type="password" id="passwd" name="passwd" required></input>
							</div>
							<div class="col-3" id="password_error"> </div>
						</div>
						
						<div class="row form-group">
							<div class="col-3">
								<label for="passwd2">Confirm Password*</label>
							</div>
							<div class="col-6">
								<input class="form-input-element" type="password" id="passwd2" name="passwd2" required></input>
							</div>
							<div class="col-3" id="password2_error"></div>
						</div>
				</fieldset><br>
				<fieldset>
					<legend>Personal Information</legend>
					<div class="row form-group">
						<div class="col-3">
								<label for="fname">First Name*</label>
							</div>
						<div class="col-6">
							<input class = "form-input-element" type="text" id="fname" name="fname" placeholder="First Name" required></input>
						</div>
						<div class="col-3" id="firstName_error"></div>
					</div>
					<div class="row form-group">
						<div class="col-3">
							<label  for="lname">Last Name*</label>
						</div>
						<div class="col-6">
							<input class = "form-input-element" type="text" id="lname" name="lname" placeholder="Last Name" required></input>
						</div>
						<div class="col-3" id="lastName_error"></div>
					</div>
					<div class="row form-group">
						<div class="col-3">
								<label for="gender">Gender*</label>
							</div>
						<div class="col-6">
							<label class="radio-inline"><input id="male" type="radio" name="gender" value="M" checked="checked">Male</label>
							<label class="radio-inline"><input type="radio" name="gender" value="F">Female</label>
						</div>
						<div class="col-3"></div>
					</div>
					
					<div class="row form-group">
						<div class="col-3">
							<label for="dob">Date of Birth*</label>
						</div>
						<div class="col-6">
							<input style="color:black;background-color: pink;" type="date" id="dob" name="dob" required></input>
						</div>
						<div class="col-3" id="dob_error">(yyyy/MM/dd)</div>
					</div>
				</fieldset><br>
				<div class="row">
						<div class="col-12" id ="status"></div>
					</div>
				<div class="row">
					<div class="col-5"></div>
					<div class="col-2">
						<span class="button button-info"  onclick="signupCall()">Register</span>
					</div>
					<div class="col-5"></div>
				</div>
				<br><br><br>
			</form>
		</div>
		<div class="col-1"></div>
	</div>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
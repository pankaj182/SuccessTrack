<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<script type="text/javascript" src="js/check.js"></script>
<script>
function loginCall(){
	var xmlHttp = new XMLHttpRequest();
	var emailid = document.getElementById('emailid').value;
	var password = document.getElementById('password').value;
	var login_type = "candidate";
	if(document.getElementById('asAdmin').checked)
		login_type = "admin";
	var url = "Login";
	var data = "emailid="+emailid+"&password="+password+"&login_type="+login_type;
	
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange=function()
	  {
	      if (xmlHttp.readyState==4 && xmlHttp.status==200){
	    	  if(xmlHttp.responseText.includes("success")){
	    		  messageAlert("login successful,redirecting now...",30000);
	    		  window.location="Home";
	    	  }
	    	  else
	    	  document.getElementById('status').innerHTML = xmlHttp.responseText;
	      }
	  }
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(data);
}
function messageAlert(msg,duration){
	 var el = document.createElement("div");
	 el.innerHTML = msg;
	 setTimeout(function(){
	  el.parentNode.removeChild(el);
	 },duration);
	 document.getElementById('status').appendChild(el);
}
</script>
<title>login</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="grid-container">
		<div class="row">

			<div class="col-1"></div>
			<div class="col-5"></div>

			<div class="col-5 form-container">
				<form class="form-horizontal" name="loginForm" method="POST" action="login.jsp">
					<div class="col-3"></div>
					<div class="col-9">
						<label class="radio-inline">
							<input type="radio" id="asAdmin" name="login_type" value="Admin" checked="checked">asAdmin
						</label> 
						<label class="radio-inline">
							<input type="radio" id="asCandidate" name="login_type" value="Candidate">as Candidate
						</label>
					</div>
					<div class="row form-group">
						<div class="col-3">
							<label for="emailid">Email Id*</label>
						</div>
						<div class="col-6">
							<input class="form-input-element" type="email" id="emailid" name="emailid" required="required" onblur="validate('loginForm','email','emailid','email_error');"></input>
						</div>
						<div class="col-3" id="email_error"></div>
					</div>

					<div class="row form-group">
						<div class="col-3">
							<label for="password">Password*</label>
						</div>
						<div class="col-6">
							<input class="form-input-element" type="password" id="password"
								name="password" required="required"></input>
						</div>
						<div class="col-3" id="password_error"></div>
					</div>
					<div class="row">
						<div class="col-12" id ="status"></div>
						<%-- <%
							${error_message}
							request.removeAttribute("error_message");
						%> --%>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-3">
							<input class="button button-info" type="reset" value="Reset">
						</div>
						<div class="col-3">
							<span class="button button-info"  onclick="loginCall()">Login</span>
						</div>
						<div class="col-3"></div>
					</div>
				</form>
			</div>
			<div class="col-1"></div>
		</div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
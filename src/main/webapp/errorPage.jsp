<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<script type="text/javascript" src = "js/test.js" ></script>
	<script type="text/javascript">
		function backgroundtext(errorCode,message){
			var element = document.getElementById("error-container");
			
			var textDiv = document.createElement("div");
			
			textDiv.innerHTML = errorCode;
			textDiv.style.position = "absolute";
			textDiv.style.top = "50%";
			textDiv.style.left = "50%";
			textDiv.style.fontSize = "100px";
			textDiv.style.color = "white";
			textDiv.style.transform = "translate(-50%,-50%)";
			textDiv.style.zIndex = 2;
			element.appendChild(textDiv);
			
			var errorDiv = document.createElement("div");
			
			errorDiv.innerHTML = message;
			errorDiv.style.position = "absolute";
			errorDiv.style.top = "20%";
			errorDiv.style.left = "10%";
			errorDiv.style.fontSize = "15px";
			errorDiv.style.color = "#ad2278";
			errorDiv.style.zIndex = 3;
			element.appendChild(errorDiv);
		}
	</script>
</head>
	<body onload ="backgroundtext('<%=response.getStatus()%>','${error_message}')" class="errorpage" >
		<div id = "blank"></div>
		<div id="error-container">
		</div>
		<div id = "blank"></div>
</body>

</html>
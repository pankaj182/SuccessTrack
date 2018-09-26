<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" isErrorPage="true"%>
<!DOCTYPE html >
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<title>Oops! page Not found</title>
	<script type="text/javascript">
		function backgroundtext(errorCode){
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
		}
	</script>
</head>
<body onload ="backgroundtext('404')" class="errorpage" >
		<div id = "blank"></div>
		<div id="error-container">
		</div>
		<div id = "blank"></div>
</body>
</html>
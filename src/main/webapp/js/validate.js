// adding event Listeners

document.addEventListener("blur", isValidEmail, true);
document.addEventListener("blur", passwordLengthVerify, true);
document.addEventListener("blur", passwordMatchVerify,true);
document.addEventListener("blur", isValidZip, true);
document.addEventListener("blur", isTextOnly, true);
document.addEventListener("blur", isEmpty, true);
document.addEventListener("blur", isValidPhone, true);
document.addEventListener("blur", isValidDate, true);
document.addEventListener("blur", isChronologized, true);

function isValidEmail(emailElement) {
	var re = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}$/igm;
	if (emailElement.value == "" || !re.test(emailElement.value))
		return false;
	return true;
}

function passwordLengthVerify(passwordElement) {
	if (passwordElement.value.length < 6)
		return false;
	return true;
}

function passwordMatchVerify(passwordElement1, passwordElement2) {
	if (passwordElement1.value == passwordElement2.value)
		return true;
	return false;
}

function isValidZip(pinElement) {
	var pinPattern = /^[0-9]{6,6}$/;
	if (!pinPattern.test(pinElement.value))
		return false;
	return true;
}

function isTextOnly(textElement) {
	var pat = /^[A-Za-z]+$/;
	if (!pat.test(textElement.value))
		return false;
	return true;
}
function isEmpty(inputElement) {
	if (inputElement.value == "")
		return false;
	return true;
}
//function isValidPhone(inputElement) {
//	var pat = /^[1-9]{1,1}[0-9]{9,9}$/;
//	if (!pat.test(inputElement.value))
//		return false;
//	return true;
//}

//function isValidDate(inputElement) {
//	var dateString = inputElement.toString();
//	var pat = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
//	if (!pat.test(dateString))
//		return false;
//
//	if (dateString.contains("/"))
//		var parts = dateString.split("/");
//	else if (dateString.contains("-"))
//		var parts = dateString.split("-");
//	else if (dateString.contains(":"))
//		var parts = dateString.split(":");
//	else if (dateString.contains("."))
//		var parts = dateString.split(".")
//	else
//		var parts = dateString.split(" ");
//
//	var day = parseInt(parts[1], 10);
//	var month = parseInt(parts[0], 10);
//	var year = parseInt(parts[2], 10);
//
//	if (year < 1000 || year > 3000 || month == 0 || month > 12)
//		return false;
//
//	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
//	// for leap year
//	if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
//		monthLength[1] = 29;
//
//	return (day > 0 && day <= monthLength[month - 1]);
//}
//
//function isChronologized(startDateElement, endDateElement){
//	var startDate = startDateElement.toString();
//	var endDate   =  endDateElement.toString();
//	
//	var msStartDate = Date.parse(startDate);
//	var msEndDate   = Date.parse(endDate);
//	if(msStartDate < msEndDate)
//		return false;
//	return true;
//}
//------------------------------Changing css---------------------------
function changeCSS(functionName,inputElement, messageElement,status){
	if(status == false){
		inputElement.style.border = "1px solid red";
		messageElement.innerHTML = getErrorMessage(functionName);
	}
	else{
		messageElement.innerHTML = "";
		inputElement.style.border = "2px solid green";
	}
}

function getErrorMessage(functionName){
	var message = "";
	if(functionName == "isEmpty")
		message = "Required";
	else if(functionName == "isValidEmail")
		message = "Invalid Email";
	else if(functionName == "isTextOnly")
		message = "Alphabets only";
	else if(functionName == "passwordLengthVerify")
		message = "Minimum 6 characters Required";
	else if(functionName == "passwordMatchVerify")
		message = "passwords not matching";
	else if(functionName == "isValidZip")
		message = "Invalid ZIP";
	else if(functionName == "isValidPhone")
		message = "Invalid Phone";
	else if(functionName == "isValidDate")
		message = "Invalid Date";
	else if(functionName == "isChronologized")
		message = "Invalid Chronology";
	else
		message = "";
	return message;
}
//-----------------------Validating Forms---------------------
function validateSignup(){
	var emailElement = document.forms["signupForm"]["emailid"];
	var password1Element = document.forms["signupForm"]["passwd"];
	var password2Element = document.forms["signupForm"]["passwd2"];
	var fnameElement = document.forms["signupForm"]["fname"];
	var lnameElement = document.forms["signupForm"]["lname"];
	var dobElement = document.forms["signupForm"]["dob"];
	
	var email_error = document.getElementById("email_error");
	var password_error = document.getElementById("password_error");
	var password2_error = document.getElementById("password2_error");
	var firstName_error = document.getElementById("firstName_error");
	var lastName_error = document.getElementById("lastName_error");
//	var dob_error = document.getElementById("dob_error");
	
	changeCSS("isEmpty",emailElement,email_error,isEmpty(emailElement));
	changeCSS("isEmpty",password1Element,password_error,isEmpty(password1Element));
	changeCSS("isEmpty",password2Element,password2_error,isEmpty(password2Element));
	changeCSS("isEmpty",fnameElement,firstName_error,isEmpty(fnameElement));
	changeCSS("isEmpty",lnameElement,lastName_error,isEmpty(lnameElement));
	changeCSS("isEmpty",dobElement,dob_error,isEmpty(dobElement));
	
	changeCSS("isValidEmail",emailElement,email_error,isValidEmail(emailElement));
	changeCSS("passwordLengthVerify",password1Element,password_error,passwordLengthVerify(password1Element));
	changeCSS("passwordMatchVerify",password2Element,password2_error,passwordMatchVerify(password1Element,password2Element));
	changeCSS("isTextOnly",fnameElement,firstName_error,isTextOnly(fnameElement));
	changeCSS("isTextOnly",lnameElement,lastName_error,isTextOnly(lnameElement));
//	changeCSS("isValidDate",dobElement,dob_error,isValidDate(dobElement));
}

//function validateLogin(){
//	var emailElement = document.forms["loginForm"]["emailid"];
//	var passwordElement = document.forms["loginForm"]["password"];
//	
//	var email_error = document.getElementById("email_error");
//	var password_error = document.getElementById("password_error");
//	
//	changeCSS("isEmpty",emailElement,email_error,isEmpty(emailElement));
//	changeCSS("isEmpty",passwordElement,password_error,isEmpty(passwordElement));
//	
//	changeCSS("isValidEmail",emailElement,email_error,isValidEmail(emailElement));
//};

//document.addEventListener("blur", emailVerify, true);
//document.addEventListener("blur", passwordVerify, true);
//document.addEventListener("blur", firstNameVerify,true);
//document.addEventListener("blur", lastNameVerify, true);

//Listener Functions
//function emailVerify() {
//	var email = document.forms["signupForm"]["emailid"];
//	var email_error = document.getElementById("email_error");
//	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
//	if (email.value == "" || !re.test(email.value)) {
//		email.style.border = "1px solid red";
//		// document.getElementById('email_div').style.color = "red";
//		email_error.textContent = "Invalid Email";
//		return false;
//	} else {
//		email.style.border = "2px solid green";
//		email_error.textContent = "";
//		return true;
//	}
//}

//function passwordVerify() {
//	var password = document.forms["signupForm"]["passwd"];
//	var password2 = document.forms["signupForm"]["passwd2"];
//	var password2_error = document.getElementById("password2_error");
//	if (password.value.length < 6) {
//		password_error.innerHTML = "Minimum 6 characters required";
//		password.style.border = "1px solid red";
//		return false;
//	} else {
//		password_error.innerHTML = "";
//		password.style.border = "2px solid green";
//	}
//	if (password.value != password2.value) {
//		password.style.border = "1px solid red";
//		password2.style.border = "1px solid red";
//		password2_error.innerHTML = "The two passwords do not match";
//		return false;
//	}
//	if (password.value == password2.value) {
//		password2_error.innerHTML = "";
//		password.style.border = "2px solid green";
//		password2.style.border = "2px solid green";
//		return true;
//	}
//}
//function firstNameVerify() {
//	var firstName = document.forms["signupForm"]["fname"];
//	var firstName_error = document.getElementById("firstName_error");
//	var pat = /^[A-Za-z]+$/;
//	if (firstName.value == "") {
//		firstName.style.border = "1px solid red";
//		firstName_error.innerHTML = "Required";
//		return false;
//	}
//	if (!pat.test(firstName.value)) {
//		firstName.style.border = "1px solid red";
//		firstName_error.innerHTML = "alphabet only";
//		return false;
//	} else {
//		firstName_error.innerHTML = "";
//		firstName.style.border = "2px solid green";
//		return true;
//	}
//}
//function lastNameVerify() {
//	var lastName = document.forms["signupForm"]["lname"];
//	var lastName_error = document.getElementById("lastName_error");
//	var pat = /^[A-Za-z]+$/;
//	if (lastName.value == "") {
//		lastName.style.border = "1px solid red";
//		lastName_error.innerHTML = "Required";
//		return false;
//	}
//	if (!pat.test(lastName.value)) {
//		lastName.style.border = "1px solid red";
//		lastName_error.innerHTML = "alphabet only";
//		return false;
//	} else {
//		lastName_error.innerHTML = "";
//		lastName.style.border = "2px solid green";
//		return true;
//	}
//}
//function cityVerify() {
//	var city = document.forms["signupForm"]["city"];
//	var city_error = document.getElementById("city_error");
//	var pat = /^[A-Za-z]+$/;
//	if (city.value == "") {
//		city.style.border = "1px solid red";
//		city_error.innerHTML = "Required";
//		return false;
//	}
//	if (!pat.test(city.value)) {
//		city.style.border = "1px solid red";
//		city_error.innerHTML = "alphabet only";
//		return false;
//	} else {
//		city_error.innerHTML = "";
//		city.style.border = "2px solid green";
//		return true;
//	}
//}
//function pinVerify() {
//	var pin = document.forms["signupForm"]["pincode"];
//	var pin_error = document.getElementById("pincode_error");
//	var pinPattern = /^[0-9]{6,6}$/;
//	if (!pinPattern.test(pin.value)) {
//		pin.style.border = "1px solid red";
//		pin_error.innerHTML = "6 digits required";
//		return false;
//	} else {
//		pin_error.innerHTML = "";
//		pin.style.border = "2px solid green";
//		return true;
//	}
//}

function emailVerify(formName,emailElement,emailErrorElement) {
	var email = document.forms[formName][emailErrorElement];
	var email_error = document.getElementById(emailErrorElement);
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
	if (email.value == "" || !re.test(email.value)) {
		email.style.border = "1px solid red";
		// document.getElementById('email_div').style.color = "red";
		email_error.textContent = "Invalid Email";
		return false;
	} else {
		email.style.border = "2px solid green";
		email_error.textContent = "";
		return true;
	}
}

// --------------------------------------validation functions-------------------

//// adding event Listeners
//
//document.addEventListener("blur", passwordLengthVerify, true);
//
//function passwordLengthVerify(passwordElement) {
//	if (passwordElement.value.length < 6)
//		return false;
//	return true;
//}
//
//function changeCSS(functionName,inputElement, messageElement,status){
//	if(status == false){
//		inputElement.style.border = "1px solid red";
//		messageElement.innerHTML = getErrorMessage(functionName);
//	}
//	else{
//		messageElement.innerHTML = "";
//		inputElement.style.border = "2px solid green";
//	}
//}
//
//function getErrorMessage(functionName){
//	var message = "";
//	if(functionName == "isEmpty")
//		message = "Required";
//	else if(functionName == "isValidEmail")
//		message = "Invalid Email";
//	else
//		message = "";
//	return message;
//}
//
//function validateSignup(){
//	var password1Element = document.forms["signupForm"]["passwd"];
//	var password_error = document.getElementById("password_error");
//	
//	changeCSS("isEmpty",password1Element,password_error,isEmpty(password1Element));	
//	changeCSS("passwordLengthVerify",password1Element,password_error,passwordLengthVerify(password1Element));
//	
//}
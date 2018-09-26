
function validate(formName,inputType,inputElementId,errorElementId){
	var element = document.forms[formName][inputElementId];
	var errorElement = document.getElementById(errorElementId);
	var status = false;
	if(inputType == "email")
		status = emailVerify(element);
	else if(inputType == "text")
		status = textVerify(element);
	if(status){
		element.style.border = "2px solid green";
		errorElement.textContent = ""
	}
	else{
		element.style.border = "1px solid red";
		printErrorMessage(errorElement,inputType);
	}
}

function emailVerify(element){
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
	if (element.value == "" || !re.test(element.value)) {
		return false;
	}
	return true;
}
function textVerify(element){
	var pat = /^[A-Za-z ]+$/;
	if (element.value == "" || !pat.test(element.value))
		return false;
	return true;
}
//function emailVerify(formName,emailElement,emailErrorElement) {
//	var email = document.forms[formName][emailElement];
//	var email_error = document.getElementById(emailErrorElement);
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

function passwordLengthVerify(formName,inputElementId,errorElementId){
	var password = document.forms[formName][inputElementId];
	var password_error = document.getElementById(errorElementId);
	if(password.value.length == 0)
		return false;
}
function printErrorMessage(errorElement,inputType){
	var message = "";
	if(inputType == "email"){
		message = "Invalid email";
	}
	else if(inputType == "text"){
		message = "alphabet only";
	}
	errorElement.textContent = message;
}

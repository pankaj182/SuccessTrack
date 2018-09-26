/**
 * 
 */

function createRequisitionButton(user_type){
	var isAdmin = (user_type == 'admin');
	if(isAdmin){
		var placeholderElement = document.getElementById("requisitionButton");
		var anchor = document.createElement('a');
		anchor.setAttribute('href',"requisitionForm.jsp");
		anchor.setAttribute('class',"button button-info");
		anchor.innerHTML = "create Requisition";
		placeholderElement.appendChild(anchor);
	}
}

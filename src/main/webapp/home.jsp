<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sf.model.Requisition"%>
<%
	if (session.getAttribute("user") == null)
		response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<title>Jobs</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<script type="text/javascript" src="js/test.js"></script>
<script type="text/javascript" src = "js/elements.js"></script>
<script type="text/javascript">
function loadUserInfo(divId,userId)
{
  var xmlhttp;
  var url="UserLookup?Id="+userId;
  xmlhttp=new XMLHttpRequest();
  xmlhttp.onreadystatechange=function()
  {
      if (xmlhttp.readyState==4 && xmlhttp.status==200){
    	  var message = xmlhttp.responseText;
    	  message = message.substring(message.indexOf("|")+1,message.length);
          document.getElementById(divId).innerHTML=message;
      }
  }

  xmlhttp.open("GET", url, true);
  xmlhttp.send();
}
function reset(id,content){
	 document.getElementById(id).innerHTML=content;
}
function checkIfApplied(reqId,elementId,){
	var xmlhttp=new XMLHttpRequest();
	var url = "RequisitionAppliedStatusChecker";
	var data = "reqId="+reqId;
	
	xmlhttp.open("POST",url,true);
	
	xmlhttp.onreadystatechange=function()
	 {
	      if (xmlhttp.readyState==4 && xmlhttp.status==200){
	    	  var message = xmlhttp.responseText;
	    	  if(message.includes("true")){
	    		  	var anchor = elementId.children[0];
	    			anchor.removeAttribute('href');
	    			anchor.style.backgroundColor="#808080";
	    			anchor.style.cursor = "default";
	    			anchor.innerHTML = "applied";
	    	  }
	      }
	  }
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send(data);
}
</script>
</head>
<body>
	<jsp:include page="_header2.jsp"></jsp:include>
	<!-- <div id="display" onmouseover="loadUserInfo('display','3')" onmouseout="reset('display')">Click me</div> -->
	
	<div class="row">
		<div class="col-5"></div>
		<%String user_type = (String)session.getAttribute("user_type");%>
		<div class="col-2" id="requisitionButton">
		<script>createRequisitionButton('<%=user_type%>');</script>
			<!-- <form name="createJobs" method="POST" action="RequisitionCreator">
				<input type="hidden" name="formName" value="createJobs"></input> <input class="button button-info" type="submit"
					value="create New Requisition"></input>
			</form> -->
		</div>
		<div class="col-5"></div>
	</div>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<table class="table">
				<tr>
					<th>ReqId</th>
					<th>Originator</th>
					<th>Job Title</th>
					<th>Creation Date</th>
					<th>Start Date</th>
					<th>End Date</th>
					<%if(user_type=="admin") {%>
						<th>#Applicants</th>
					<%} %>
					<th></th>
				</tr>
				<%
					ArrayList<Requisition> reqList = null;
					if (request.getAttribute("reqList") instanceof List) {
						reqList = (ArrayList<Requisition>) request.getAttribute("reqList");
					}
					if (reqList != null) {
						for (Requisition req : reqList) {
				%>
				<tr>
					<td><%=req.getRequisitonId()%></td>
					<td width="50px" id="<%=req.getRequisitonId()%>" onmouseover="loadUserInfo('<%=req.getRequisitonId()%>','<%=req.getOriginatorId()%>')" ><%=req.getOriginatorId()%></td>
					<td><%=req.getJobTitle()%></td>
					<td><%=req.getCreationDate()%></td>
					<td><%=req.getStartDate() %>
					<td><%=req.getEndDate() %></td>
					<%if(user_type=="admin") {%>
						<td><a href="CandidateListForRequisition?reqId=<%=req.getRequisitonId()%>"><%=req.getApplicationCount() %></a></td>
					<%} %>
					<td onmouseover="checkIfApplied('<%=req.getRequisitonId()%>',this)"><a  class="button button-info" href="applicationForm.jsp?reqId=<%=req.getRequisitonId()%>">apply</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
		<div class="col-1"></div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
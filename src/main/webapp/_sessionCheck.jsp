<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%
	if (session.getAttribute("user") == null)
		response.sendRedirect("index.jsp");
%>
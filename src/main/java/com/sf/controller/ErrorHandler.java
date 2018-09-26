package com.sf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer sb = new StringBuffer();

		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

		if (servletName == null) {
			servletName = "Unknown";
		}
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		if(statusCode != 500){
			sb.append("status code : "+statusCode);
			sb.append("Requested URI : "+requestUri);
	      }else{
	    	  sb.append("\n servlet Name "+servletName);
	    	  sb.append("\n Exception Name"+throwable.getClass().getName());
	    	  sb.append("\n Requested URI : "+requestUri);
	    	  sb.append("Exception Message:"+throwable.getMessage());
	      }
		
		
		request.setAttribute("error_message", sb.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest requestuest, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

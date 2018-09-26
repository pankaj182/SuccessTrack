package com.sf.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sf.model.User;
import com.sf.service.CandidateProfileServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.CandidateProfileServicesImpl;

public class UserLookup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		CandidateProfileServices cps = null;
		int userId = -1;
		User user = null;
		StringBuffer sb = null;
		try {
			userId = Integer.parseInt(request.getParameter("Id"));
			cps = new CandidateProfileServicesImpl();
			user = cps.getUserDataByUserId(userId);
			sb = new StringBuffer();
			// sb.append("<table>");
			if (user.getBase64encodedDP() != "" || user.getBase64encodedDP() != null)
				// sb.append("<tr></td rowspan='2'><img
				// src='data:image/jpeg;base64,"+user.getBase64encodedDP()+"'"+"height='350px'
				// width='100%'/></td></tr>");
				// sb.append("<br> EmailId: "+user.getEmailId());
				// sb.append(user.getFirstName()+" "+user.getLastName());
				// sb.append("</table>");
				sb.append("|");
			sb.append(user.getFirstName() + " " + user.getLastName());
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(sb.toString());
		} catch (NullPointerException e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (ServiceExceptions e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.sf.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sf.authentication.LogonAuthenticator;
import com.sf.model.User;
import com.sf.service.CandidateProfileServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.CandidateProfileServicesImpl;

public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = null;
		String emailId = request.getParameter("emailid");
		String password = request.getParameter("passwd2");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");

		User user = new User(emailId);
		user.setDob(dob);
		user.setName(firstName, lastName);
		user.setGender(gender);
		user.setPassword(password);
		user.setUserType("candidate");

		CandidateProfileServices cps = null;
		int userId = -1;
		// if user with an existing email exists
		try {
			sb = new StringBuffer();
			if (LogonAuthenticator.userExists(emailId)) {
				sb.append("you are already registered with this email Id");
			} else {
				cps = new CandidateProfileServicesImpl();
				userId = cps.registerCandidate(user);
				if (userId != -1) {
					sb.append("success");
				} else {
					sb.append("registration failed,Please try again");
				}
			}
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
}
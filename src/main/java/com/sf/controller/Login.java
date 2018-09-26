package com.sf.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sf.authentication.LogonAuthenticator;
import com.sf.service.exceptions.ServiceExceptions;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = null;
		HttpSession session = null;
		try {
			sb = new StringBuffer();
			String emailId = request.getParameter("emailid");
			String password = request.getParameter("password");
			String login_type = request.getParameter("login_type");

			// whether email exists or not?
			if (LogonAuthenticator.userExists(emailId)) {
				// whether password correct or not
				int userId = -1;
				if ((userId = LogonAuthenticator.validatePassword(emailId, password)) != -1) {
					// if admin is logging
					if (login_type.equalsIgnoreCase("Admin")) {
						if (LogonAuthenticator.isAdmin(userId)) {
							session = request.getSession(true);
							session.setAttribute("user", userId);
							session.setMaxInactiveInterval(30 * 60);
							Cookie cookie = new Cookie("user", Integer.toString(userId));
							cookie.setMaxAge(30 * 60);
							response.addCookie(cookie);
							session.setAttribute("user_type", "admin");
							sb.append("success");

						} else { // if "not admin" logging as admin
							sb.append("Not an admin");
						}
					} else if (login_type.equalsIgnoreCase("Candidate")) {
						if (LogonAuthenticator.isAdmin(userId)) {
							sb.append("not a candidate");
						} else {
							session = request.getSession(true);
							session.setAttribute("user", userId);
							session.setMaxInactiveInterval(30 * 60);
							Cookie cookie = new Cookie("user", Integer.toString(userId));
							cookie.setMaxAge(30 * 60);
							response.addCookie(cookie);
							session.setAttribute("user_type", "candidate");

							sb.append("success");
						}
					}
				}
				// password incorrect
				else {
					sb.append("incorrect password");
				}
			} else { // no user with this email id exists
				sb.append("no user with this email id exists");
			}
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(sb.toString());
		} catch (NullPointerException e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (ServiceExceptions se) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		}

	}

}

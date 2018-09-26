package com.sf.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = null;
		try {
			session = request.getSession(false);
			if (session != null) {
				session.removeAttribute("user");
				session.removeAttribute("user_type");
				session.setMaxInactiveInterval(-1);
				session.invalidate();
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (NullPointerException e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		doGet(request, response);

	}

}

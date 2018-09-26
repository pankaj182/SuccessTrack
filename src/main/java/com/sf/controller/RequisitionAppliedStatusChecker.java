package com.sf.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sf.service.RequisitionServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.RequisitionServicesImpl;

public class RequisitionAppliedStatusChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = null;
		RequisitionServices service = null;
		StringBuffer sb = null;
		boolean applied = false;
		try {
			session = request.getSession(false);
			service = new RequisitionServicesImpl();
			sb = new StringBuffer();

			int userId = (Integer) session.getAttribute("user");
			String reqId = request.getParameter("reqId");
			applied = service.hasApplied(userId, Integer.parseInt(reqId));

			sb.append(applied);

			response.setContentType("text/html");
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

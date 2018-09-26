package com.sf.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sf.model.User;
import com.sf.service.RequisitionServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.RequisitionServicesImpl;

/**
 * Servlet implementation class CandidateListForRequisition
 */
public class CandidateListForRequisition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequisitionServices reqService = null;
		ArrayList<User> userList = null;
		
		try {
			reqService = new RequisitionServicesImpl();
			int reqId = Integer.parseInt(request.getParameter("reqId"));
			userList = reqService.getAllApplicantsForRequisition(reqId);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("candidatesForSpecificRequisition.jsp").include(request, response);
			
		} catch (NullPointerException  e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (ServiceExceptions  e) {
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

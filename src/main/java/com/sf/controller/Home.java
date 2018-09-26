package com.sf.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sf.model.Requisition;
import com.sf.service.RequisitionServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.RequisitionServicesImpl;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequisitionServices services = null;
		ArrayList<Requisition> reqList = null;
		try {
			services = new RequisitionServicesImpl();
			reqList = services.getAllRequisition();
			request.setAttribute("reqList", reqList);

			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
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

package com.sf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sf.model.Requisition;
import com.sf.service.RequisitionServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.RequisitionServicesImpl;

public class RequisitionCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequisitionServices services = null;
		RequestDispatcher rd = null;
		HttpSession session = null;
		try {
			session = request.getSession(false);
			if (session.getAttribute("user_type").toString().equalsIgnoreCase("admin")) {
				services = new RequisitionServicesImpl();

				String jobTitle = request.getParameter("jobTitle");
				String jobDescription = request.getParameter("jobDescription");
				int originatorId = Integer.parseInt(request.getParameter("originator"));
				int ctc = Integer.parseInt(request.getParameter("ctc"));
				String startDate = request.getParameter("startDate"); // yyyy/MM/dd
				String endDate = request.getParameter("endDate");
				String requiredSkills = request.getParameter("requiredSkills");

				Requisition requisition = new Requisition();
				requisition.setJobTitle(jobTitle);
				requisition.setJobDescription(jobDescription);
				requisition.setOriginatorId(originatorId);
				requisition.setCtc(ctc);
				requisition.setSkillsRequired(requiredSkills);
				requisition.setStartDate(startDate);
				requisition.setEndDate(endDate);

				services.createRequisition(requisition);

				rd = request.getRequestDispatcher("Home");
				rd.include(request, response);
			} else {

			}
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

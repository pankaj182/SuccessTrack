package com.sf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sf.model.Application;
import com.sf.service.ApplicationServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.ApplicationServicesImpl;
import com.sf.utilities.CustomFormatter;

@MultipartConfig
public class ApplicationCreator extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = null;
		Application application = null;
		ApplicationServices appService = null;
		int userId = -1;
		RequestDispatcher dispatcher = null;
		
		try {
			session = request.getSession(false);
			userId = (Integer) session.getAttribute("user");
			appService = new ApplicationServicesImpl();

			Part resumePart = request.getPart("resume");
			Part coverLetterPart = request.getPart("coverLetter");
			int reqId = (Integer.parseInt(request.getParameter("reqId")));

			application = new Application(reqId, userId);
			application.setCoverLetter(CustomFormatter.getBytesFromPart(coverLetterPart));
			application.setResume(CustomFormatter.getBytesFromPart(resumePart));
			appService.createApplication(application);

			dispatcher = request.getRequestDispatcher("Home");
			dispatcher.include(request, response);
			
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

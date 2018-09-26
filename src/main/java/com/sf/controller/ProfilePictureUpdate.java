package com.sf.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sf.model.User;
import com.sf.service.CandidateProfileServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.CandidateProfileServicesImpl;
import com.sf.utilities.CustomFormatter;

@MultipartConfig
public class ProfilePictureUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = null;
		CandidateProfileServices cps = null;
		int userId = -1;
		try {
			session = request.getSession(false);
			cps = new CandidateProfileServicesImpl();
			userId = (Integer) session.getAttribute("user");
			User user = new User(userId);
			user.setProfileImage(CustomFormatter.getBytesFromPart(request.getPart("dp")));
			cps.updateProfileImage(user);
			request.getRequestDispatcher("UserProfile").include(request, response);
		} catch (NullPointerException e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (ServiceExceptions e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		}
	}

}

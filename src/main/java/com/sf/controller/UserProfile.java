package com.sf.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sf.model.CandidateBackground;
import com.sf.model.User;
import com.sf.service.CandidateProfileServices;
import com.sf.service.exceptions.ServiceExceptions;
import com.sf.service.impl.CandidateProfileServicesImpl;

public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = null;
		int userId = -1;
		CandidateProfileServices cps = null;
		try {
			session = request.getSession(false);
			userId = (Integer) session.getAttribute("user");
			cps = new CandidateProfileServicesImpl();
			// Form Submission
			String formName = request.getParameter("formName");
			if (formName == null) {
			} else {
				switch (formName) {
				case "educationInfo":
					CandidateBackground edu = new CandidateBackground(userId, request.getParameter("instituteName"),
							request.getParameter("degree"), request.getParameter("major"),
							Double.parseDouble(request.getParameter("cgpa")),
							Double.parseDouble(request.getParameter("percentage")));
					edu.setDates(request.getParameter("startDate"), request.getParameter("endDate"));
					edu.setAddress(request.getParameter("eduCity"), request.getParameter("eduState"),
							request.getParameter("eduCountry"));
					cps.registerBackgroundInformation(edu);
					break;
				case "experienceInfo":
					CandidateBackground exp = new CandidateBackground(userId, request.getParameter("companyName"),
							request.getParameter("jobTitle"), Integer.parseInt(request.getParameter("currentSalary")));
					exp.setDates(request.getParameter("startDate"), request.getParameter("endDate"));
					exp.setAddress(request.getParameter("jobCity"), request.getParameter("jobState"),
							request.getParameter("jobCountry"));
					cps.registerBackgroundInformation(exp);
					break;
				case "skillsInfo":
					CandidateBackground skills = new CandidateBackground(userId);
					skills.setSkillSet(request.getParameter("skills"));
					cps.registerBackgroundInformation(skills);
					break;
				default:
				}
			}
			// Profile Display
			displayProfile(request, response, userId);

			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			dispatcher.include(request, response);
		} catch (NullPointerException e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (ServiceExceptions e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("ErrorHandler").include(request, response);
		}
	}

	private void displayProfile(HttpServletRequest request, HttpServletResponse response, int userId) throws ServiceExceptions {
		ArrayList<CandidateBackground> userProfile = null;
		User user = null;
		CandidateProfileServices cps = null;
		cps = new CandidateProfileServicesImpl();
		userProfile = cps.getUserBackgroundByUserId(userId);
		user = cps.getUserDataByUserId(userId);
		request.setAttribute("userProfile", userProfile);
		request.setAttribute("userPersonal", user);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

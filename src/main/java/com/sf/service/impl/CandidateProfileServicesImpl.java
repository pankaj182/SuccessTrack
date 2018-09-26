package com.sf.service.impl;

import java.util.ArrayList;

import com.sf.dao.CandidateRegisterDao;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.dao.impl.CandidateRegisterDaoImpl;
import com.sf.model.CandidateBackground;
import com.sf.model.User;
import com.sf.service.CandidateProfileServices;
import com.sf.service.exceptions.ServiceExceptions;

public class CandidateProfileServicesImpl implements CandidateProfileServices {

	@Override
	public int registerCandidate(User user) throws ServiceExceptions {
		CandidateRegisterDao dao = null;
		int userId = -1;
		if (user == null)
			throw new IllegalArgumentException("null user instance");
		try {
			dao = new CandidateRegisterDaoImpl();
			userId = dao.registerUser(user);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return userId;
	}

	@Override
	public User getUserDataByUserId(int userId) throws ServiceExceptions {
		User user = null;
		CandidateRegisterDao dao = null;
		if (userId < 0)
			throw new IllegalArgumentException(" invalid user Id");
		try {
			dao = new CandidateRegisterDaoImpl();
			user = dao.getUserDataByUserId(userId);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return user;
	}

	@Override
	public void registerBackgroundInformation(CandidateBackground cb) throws ServiceExceptions {
		CandidateRegisterDao dao = null;
		if (cb == null)
			throw new IllegalArgumentException("null instance of method parameter");
		try {
			dao = new CandidateRegisterDaoImpl();
			dao.insertBackgroundData(cb);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
	}

	@Override
	public ArrayList<CandidateBackground> getUserBackgroundByUserId(int userId) throws ServiceExceptions {
		CandidateRegisterDao dao = null;
		ArrayList<CandidateBackground> list = null;
		if (userId < 0)
			throw new IllegalArgumentException(" invalid user Id");
		try {
			dao = new CandidateRegisterDaoImpl();
			list = dao.getUserBackgroundByUserId(userId);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return list;
	}

	@Override
	public void updateProfileImage(User user) throws ServiceExceptions {
		CandidateRegisterDao dao = null;
		if (user == null)
			throw new IllegalArgumentException("null instance of method parameter");
		try {
			dao = new CandidateRegisterDaoImpl();
			dao.updateProfilePicture(user);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
	}
}

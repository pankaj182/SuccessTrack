package com.sf.service.impl;

import java.util.ArrayList;

import com.sf.dao.RequisitionDao;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.dao.impl.RequisitionDaoImpl;
import com.sf.model.Requisition;
import com.sf.model.User;
import com.sf.service.RequisitionServices;
import com.sf.service.exceptions.ServiceExceptions;

public class RequisitionServicesImpl implements RequisitionServices {

	@Override
	public boolean createRequisition(Requisition requisition) throws ServiceExceptions {
		boolean status = false;
		RequisitionDao dao = null;
		if (requisition == null)
			throw new IllegalArgumentException("null instance of method parameter");
		try {
			dao = new RequisitionDaoImpl();
			status = dao.insertRequisition(requisition);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return status;
	}

	@Override
	public ArrayList<Requisition> getAllRequisition() throws ServiceExceptions {
		RequisitionDao dao = null;
		ArrayList<Requisition> reqList = null;
		try {
			dao = new RequisitionDaoImpl();
			reqList = dao.getAllRequisition();
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return reqList;
	}

	@Override
	public boolean hasApplied(int userId, int reqId) throws ServiceExceptions {
		RequisitionDao dao = null;
		boolean status = false;
		try {
			dao = new RequisitionDaoImpl();
			status = dao.hasApplied(userId, reqId);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return status;
	}

	public ArrayList<User> getAllApplicantsForRequisition(int reqId) throws ServiceExceptions {
		ArrayList<User> userList = null;
		RequisitionDao dao = null;
		try {
			dao = new RequisitionDaoImpl();
			userList = dao.getAllApplicantsForRequisition(reqId);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return userList;
	}
}

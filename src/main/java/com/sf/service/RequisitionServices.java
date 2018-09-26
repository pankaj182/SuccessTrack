package com.sf.service;

import java.util.ArrayList;

import com.sf.model.Requisition;
import com.sf.model.User;
import com.sf.service.exceptions.ServiceExceptions;

public interface RequisitionServices {
	public boolean createRequisition(Requisition requisition) throws ServiceExceptions;
	public ArrayList<Requisition> getAllRequisition() throws ServiceExceptions;
	public boolean hasApplied(int userId, int reqId) throws ServiceExceptions;
	public ArrayList<User> getAllApplicantsForRequisition(int reqId) throws ServiceExceptions;
}
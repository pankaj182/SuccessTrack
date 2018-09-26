package com.sf.dao;

import java.util.ArrayList;

import com.sf.dao.exceptions.DatabaseException;
import com.sf.model.Requisition;
import com.sf.model.User;
/**
 * 
 * @author I348491
 *
 */
public interface RequisitionDao {
	public ArrayList<Requisition> getAllRequisition() throws DatabaseException;

	boolean insertRequisition(Requisition requisition) throws DatabaseException;

	public boolean hasApplied(int userId, int reqId) throws DatabaseException;

	public ArrayList<User> getAllApplicantsForRequisition(int reqId) throws DatabaseException;
}

package com.sf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import com.sf.connection.ConnectionProvider;
import com.sf.connection.impl.ConnectionProviderImpl;
import com.sf.dao.CandidateRegisterDao;
import com.sf.dao.RequisitionDao;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.model.Requisition;
import com.sf.model.User;
import com.sf.utilities.DateUtilities;

public class RequisitionDaoImpl implements RequisitionDao {

	@Override
	public ArrayList<Requisition> getAllRequisition() throws DatabaseException {
		Connection conn = null;
		ResultSet rs = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		ArrayList<Requisition> reqList = null;
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select REQ_ID,ORIGINATOR_ID,JOB_TITLE,CREATION_DATE,START_DATE,END_DATE from REQUISITION");
			rs = ps.executeQuery();
			reqList = new ArrayList<Requisition>();

			while (rs.next()) {
				Requisition req = new Requisition(rs.getInt("REQ_ID"), rs.getInt("ORIGINATOR_ID"));
				req.setCreationDate(DateUtilities.getPresentableDate(rs.getString("CREATION_DATE")));
				req.setJobTitle(rs.getString("JOB_TITLE"));
				req.setStartDate(DateUtilities.getPresentableDate(rs.getString("START_DATE")));
				req.setEndDate(DateUtilities.getPresentableDate(rs.getString("END_DATE")));
				req.setApplicationCount(getApplicationCountForRequisition(rs.getInt("REQ_ID")));
				reqList.add(req);
			}
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Exceptions while fetching all requisitions", e);
		} catch (SQLException se) {
			throw new DatabaseException("Exceptions while fetching all requisitions", se);
		} catch (Exception exception) {
			throw new DatabaseException("Exceptions while fetching all requisitions", exception);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return reqList;
	}

	@Override
	public boolean insertRequisition(Requisition requisition) throws DatabaseException {
		Connection conn = null;
		int status = 0;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		if( requisition == null)
			throw new IllegalArgumentException("Null Requisition Object , at "+getClass().getName()+" "+getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();

			ps = conn.prepareStatement(
					"Insert into REQUISITION values(REQ_ID_SEQ.nextVal,?,?,?,?,?,?,to_date(?, 'yyyy/MM/dd'),to_date(?, 'yyyy/MM/dd'),to_date(?, 'yyyy/MM/dd'))");
			ps.setInt(1, requisition.getOriginatorId()); // validation Required
			ps.setString(2, requisition.getJobTitle());
			ps.setString(3, requisition.getJobDescription());
			ps.setInt(4, requisition.getCtc());
			ps.setString(5, requisition.getSkillsRequired());
			int isActive = (DateUtilities.isCurrentDateInRangeOf(requisition.getStartDate(), requisition.getEndDate())
					? 1
					: 0);
			ps.setInt(6, isActive);
			ps.setString(7, requisition.getStartDate());
			ps.setString(8, requisition.getEndDate());
			ps.setString(9, requisition.getCreationDate());
			status = ps.executeUpdate();
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not insert requisition", e);
		} catch (SQLException se) {
			throw new DatabaseException("Could not insert requisition", se);
		} catch (Exception exception) {
			throw new DatabaseException("Could not insert requisition", exception);
		}finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return (status > 0 ? true : false);
	}

	@Override
	public boolean hasApplied(int userId, int reqId) throws DatabaseException {
		Connection conn = null;
		ResultSet rs = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		boolean status = false;
		if(userId < 0 || reqId < 0)
			throw new IllegalArgumentException("Illegal Arguement at"+getClass().getName()+" "+getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select REQ_ID from APPLICATION WHERE CANDIDATE_ID = ? AND REQ_ID = ?");
			ps.setInt(1, userId);
			ps.setInt(2, reqId);
			rs = ps.executeQuery();

			status = rs.next();

		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("could not check application status", e);
		} catch (SQLException se) {
			throw new DatabaseException("could not check application status", se);
		} catch (Exception exception) {
			throw new DatabaseException("could not check application status", exception);
		}finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return status;
	}

	private int getApplicationCountForRequisition(int reqId) throws DatabaseException {
		int count = -1;
		Connection conn = null;
		ResultSet rs = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		if(reqId < 0)
			throw new IllegalArgumentException("Invalid Requisition Id at"+getClass().getName()+" "+getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select COUNT(CANDIDATE_ID) AS applicationCount from APPLICATION WHERE REQ_ID = ?");
			ps.setInt(1, reqId);
			rs = ps.executeQuery();
			while (rs.next())
				count = rs.getInt("applicationCount");
		}catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Exception while fetching Application count for particular Requisition", e);
		} catch (SQLException se) {
			throw new DatabaseException("Exception while fetching Application count for particular Requisition ", se);
		} catch (Exception exception) {
			throw new DatabaseException("Exception while fetching Application count for particular Requisition", exception);
		}finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return count;
	}

	@Override
	public ArrayList<User> getAllApplicantsForRequisition(int reqId) throws DatabaseException {
		ArrayList<User> userList = null;
		Connection conn = null;
		ResultSet rs = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		CandidateRegisterDao crd = null;
		try {
			crd = new CandidateRegisterDaoImpl();
			userList = new ArrayList<User>();
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select CANDIDATE_ID from APPLICATION WHERE REQ_ID = ?");
			ps.setInt(1, reqId);
			rs = ps.executeQuery();
			while (rs.next()) {
				userList.add(crd.getUserDataByUserId(rs.getInt("CANDIDATE_ID")));
			}
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Exception while fetching  applicants for the requisition", e);
		} catch (SQLException se) {
			throw new DatabaseException("Exception while fetching  applicants for the requisition", se);
		} catch (Exception exception) {
			throw new DatabaseException("Exception while fetching  applicants for the requisition", exception);
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return userList;
	}
}

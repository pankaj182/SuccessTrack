package com.sf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

import com.sf.connection.ConnectionProvider;
import com.sf.connection.impl.ConnectionProviderImpl;
import com.sf.dao.Validator;
import com.sf.dao.exceptions.DatabaseException;

public class ValidatorImpl implements Validator {

	public boolean userExists(String emailId) throws DatabaseException {
		boolean exists = false;
		Connection conn = null;
		ConnectionProvider provider = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (emailId == "" || emailId == null)
			throw new IllegalArgumentException("EmailId is null");
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select ? from USERS where email_id=?");
			ps.setString(1, "email_id");
			ps.setString(2, emailId);
			rs = ps.executeQuery();
			exists = rs.next();
		} catch (IllegalArgumentException ie) {
			throw new DatabaseException("", ie);
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Exception while checking whether emailid exists in db", e);
		} catch (SQLException se) {
			throw new DatabaseException("Exception while checking whether emailid exists in db", se);
		} catch (Exception exception) {
			throw new DatabaseException("Exception while checking whether emailid exists in db", exception);
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
		return exists;
	}

	public int passwordValid(String emailId, String password) throws DatabaseException {
		int userId = -1;
		Connection conn = null;
		ConnectionProvider provider = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (emailId == "" || emailId == null || password == "" || password == null)
			throw new IllegalArgumentException("invalid arguement");
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select user_id from USERS where email_id = ? and password = ?");
			ps.setString(1, emailId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next())
				userId = rs.getInt("user_id");
		} catch (IllegalArgumentException ie) {
			throw new DatabaseException("", ie);
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Exception while validationg password", e);
		} catch (SQLException se) {
			throw new DatabaseException("Exception while validationg password", se);
		} catch (Exception exception) {
			throw new DatabaseException("Exception while validationg password", exception);
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
		return userId;
	}

	@Override
	public String getTypeOfUser(int userId) throws DatabaseException {
		Connection conn = null;
		ConnectionProvider provider = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String userType = "";
		if (userId < 0)
			throw new IllegalArgumentException("Invalid user id");
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select USER_TYPE from USERS where USER_ID = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next())
				userType = rs.getString("USER_TYPE");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (IllegalArgumentException ie) {
				throw new DatabaseException("", ie);
			} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
				throw new DatabaseException("Exception while validationg password", e);
			} catch (SQLException se) {
				throw new DatabaseException("Exception while validationg password", se);
			} catch (Exception exception) {
				throw new DatabaseException("Exception while validationg password", exception);
			}
		}
		return userType;
	}

}

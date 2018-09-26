package com.sf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

import com.sf.connection.ConnectionProvider;
import com.sf.connection.impl.ConnectionProviderImpl;
import com.sf.dao.ApplicationDao;
import com.sf.dao.exceptions.DBConnectionException;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.model.Application;
import com.sf.utilities.DateUtilities;

public class ApplicationDaoImpl implements ApplicationDao{

	@Override
	public void insertApplication(Application application) throws DatabaseException, DBConnectionException {
		Connection conn = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		if(application == null)
			throw new IllegalArgumentException("application object is null, at "+getClass().getName() +" "+getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			
			ps = conn.prepareStatement("Insert into APPLICATION values(APP_ID_SEQ.nextVal,?,?,to_date(?, 'yyyy/MM/dd'),?,?,?)");
			ps.setInt(1, application.getCandidateId());
			ps.setInt(2, application.getRequisitionId());
			ps.setString(3, DateUtilities.getCurrentDateAsString());
			ps.setBytes(4, application.getResume());
			ps.setBytes(5, application.getCoverLetter());
			ps.setString(6, "applied");
			ps.executeUpdate();
		}
		catch(SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not insert application into Database",e);
		}
		catch(SQLException se) {
			throw new DatabaseException("Could not insert application into Database ",se);
		}
		catch(Exception exception) {
			throw new DatabaseException("Could not insert application into Database",exception);
		}
		finally {
			try {
				if(ps!= null )
					ps.close();
				if(conn != null)
					conn.close();
				
			} catch (SQLException e) {
				throw new DatabaseException("Exception",e);
			}
			
		}
	}
}

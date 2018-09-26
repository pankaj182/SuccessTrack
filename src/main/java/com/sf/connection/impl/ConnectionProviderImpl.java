package com.sf.connection.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sf.connection.ConnectionProvider;
import com.sf.connection.Provider;
import com.sf.dao.exceptions.DBConnectionException;
/**
 * <p> Helper class to make connections, close connections </p>
 * @author I348491
 * @see ConnectionProvider
 * @see #getConnection()
 * @see #closeConnection(Connection)
 */
public class ConnectionProviderImpl implements ConnectionProvider {
	
	/**
	 * Method to establish Connection with database
	 * @return Connection Object
	 * @throws DBConnectionException
	 */
	public Connection getConnection() throws DBConnectionException{
		Connection conn = null;
		try{  
			Provider p = new ProviderImpl();
			Class.forName(p.getDriverName());  
			conn=DriverManager.getConnection(p.getConnectionURL(),p.getDBUsername(),p.getDBPassword());
			if(conn == null)
				throw new DBConnectionException("Database Connection Not Established, Exception at "+getClass().getName() +" "+getClass().getEnclosingMethod());
		}
		catch(NullPointerException | ClassNotFoundException | SQLException e) {
			throw new DBConnectionException("",e);
		}
	    return conn;
	}
	/**
	 * Method to close connection
	 * @param conn Connection object which needs to be closed
	 */
	public void closeConnection(Connection conn) throws DBConnectionException {
		try {
			if(conn == null)
				throw new DBConnectionException("Closing a null connection");
			conn.close();
		}
		catch(SQLException e) {
			throw new DBConnectionException("",e);
		}
	}
}

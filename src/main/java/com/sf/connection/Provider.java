package com.sf.connection;

import com.sf.dao.exceptions.DBConnectionException;

/**
 * 
 * Interface providing necessary fields to connect to Database
 * @author I348491
 * @see DBConnectionException
 * 
 */
public interface Provider {
	
	public String getDriverName() throws DBConnectionException;
	public String getConnectionURL() throws DBConnectionException;
	public String getDBUsername() throws DBConnectionException;
	public String getDBPassword() throws DBConnectionException;
}

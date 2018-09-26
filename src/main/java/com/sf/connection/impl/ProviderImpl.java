package com.sf.connection.impl;

import com.sf.connection.Provider;
import com.sf.dao.exceptions.DBConnectionException;

/**
 * @author I348491
 */
public class ProviderImpl implements Provider{
	private String DRIVER="oracle.jdbc.driver.OracleDriver";  
	private String CONNECTION_URL="jdbc:oracle:thin:@localhost:1521:xe";  
	private String USERNAME="dummyadmin";  
	private String PASSWORD="pwd123";
	
	public String getDriverName() throws DBConnectionException {
		if(DRIVER == "" || DRIVER == null)
			throw new DBConnectionException("Invalid  Driver name");
		return DRIVER;
	}
	
	public String getConnectionURL() throws DBConnectionException {
		if(CONNECTION_URL == "" || CONNECTION_URL == null)
			throw new DBConnectionException("Connection Url is Incorrect");
		return CONNECTION_URL;
	}
	
	public String getDBUsername() throws DBConnectionException {
		if(USERNAME == "" || USERNAME == null)
			throw new DBConnectionException("Cannot connect to Database, Incorrect Username");
		return USERNAME;
	}
	
	public String getDBPassword() throws DBConnectionException{
		if(PASSWORD == "" || PASSWORD == null)
			throw new DBConnectionException("Cannot connect to Database, Incorrect Password");
		return PASSWORD;
	}
}
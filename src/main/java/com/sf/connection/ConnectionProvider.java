package com.sf.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sf.dao.exceptions.DBConnectionException;
/**
 * <p> Interface to Connect to Database </p>
 * @author I348491
 * @see #getConnection()
 * @see #closeConnection(Connection)
 * @see #closeResultSet(ResultSet)
 * @see #closePreparedStatement(PreparedStatement)
 */
public interface ConnectionProvider {
	public Connection getConnection() throws DBConnectionException;
	public void closeConnection(Connection conn) throws DBConnectionException;
}

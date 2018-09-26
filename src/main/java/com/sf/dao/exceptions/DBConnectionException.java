package com.sf.dao.exceptions;

/**
 * 
 * <p>Custom Exception Class to handle Exception while Connectiong to Database.</p>
 * @author I348491
 * 
 */
public class DBConnectionException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(Throwable cause) {
        super(cause);
    }

    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

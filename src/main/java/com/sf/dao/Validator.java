package com.sf.dao;

import com.sf.dao.exceptions.DatabaseException;

public interface Validator {
	/**
	 * @param emailId User bean containing all relevant parameters
	 * @return true if a user already exist with the given emailId, else return false.
	 * @throws DatabaseException 
	 */
	public boolean userExists(String emailId) throws DatabaseException;
	
	/**
	 * Method used to validate the user's credentials while logging in
	 * @param user bean stores the credentials
	 * @return true if User credentials are valid else returns false.
	 * @throws DatabaseException 
	 */
	public int passwordValid(String emailId,String password) throws DatabaseException;

	public String getTypeOfUser(int userId) throws DatabaseException;
}

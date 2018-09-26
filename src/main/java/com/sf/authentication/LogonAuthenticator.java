package com.sf.authentication;

import com.sf.dao.Validator;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.dao.impl.ValidatorImpl;
import com.sf.service.exceptions.ServiceExceptions;

public class LogonAuthenticator {
	public static boolean userExists(String emailId) throws ServiceExceptions {
		boolean status = false;
		if(emailId == null || emailId == "")
			throw new IllegalArgumentException("invalid arguement");
		try {
			Validator validator = new ValidatorImpl();
			status = validator.userExists(emailId);
		}catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return status;
	}

	public static int validatePassword(String emailId,String password) throws ServiceExceptions {
		int status = -1;
		try {
			Validator validator = new ValidatorImpl();
			status = validator.passwordValid(emailId,password);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return status;
	}
	public static boolean isAdmin(int userId) throws ServiceExceptions {
		boolean status = false;
		Validator validator = null;
		try {
			validator = new ValidatorImpl();
			status = (validator.getTypeOfUser(userId).equalsIgnoreCase("Admin"));
		}
		catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
		return status;
	}
}

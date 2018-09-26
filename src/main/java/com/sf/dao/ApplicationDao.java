package com.sf.dao;

import com.sf.dao.exceptions.DBConnectionException;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.model.Application;

public interface ApplicationDao {
	public void insertApplication(Application application) throws DatabaseException, DBConnectionException;
}

package com.sf.service.impl;

import com.sf.dao.ApplicationDao;
import com.sf.dao.exceptions.DBConnectionException;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.dao.impl.ApplicationDaoImpl;
import com.sf.model.Application;
import com.sf.service.ApplicationServices;
import com.sf.service.exceptions.ServiceExceptions;

public class ApplicationServicesImpl implements ApplicationServices {

	@Override
	public void createApplication(Application application) throws ServiceExceptions {
		ApplicationDao dao = null;
		if (application == null)
			throw new IllegalArgumentException("Null application instance");
		
		try {
			dao = new ApplicationDaoImpl();
			dao.insertApplication(application);
		} catch (IllegalArgumentException ie) {
			throw new ServiceExceptions(ie);
		} catch (DatabaseException | DBConnectionException dbe) {
			throw new ServiceExceptions(dbe);
		} catch (Exception e) {
			throw new ServiceExceptions(e);
		}
	}
}
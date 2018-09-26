package com.sf.service;

import com.sf.model.Application;
import com.sf.service.exceptions.ServiceExceptions;

public interface ApplicationServices {

	void createApplication(Application application) throws ServiceExceptions;

}

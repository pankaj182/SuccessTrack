package com.sf.service.exceptions;
/**
 * 
 * @author I348491
 *
 */
public class ServiceExceptions extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServiceExceptions(String message) {
		super(message);
	}

	public ServiceExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceExceptions(Throwable cause) {
		super(cause);
	}
	
	
}

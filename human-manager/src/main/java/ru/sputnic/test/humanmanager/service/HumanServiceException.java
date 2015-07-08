package ru.sputnic.test.humanmanager.service;

public class HumanServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7662018783759027214L;

	public HumanServiceException() {
		super();
	}

	public HumanServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HumanServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public HumanServiceException(String message) {
		super(message);
	}

	public HumanServiceException(Throwable cause) {
		super(cause);
	}

}

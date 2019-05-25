package io.windmill.windmill.services.exceptions;

public class SubscriptionExpiredException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5287321829167421634L;

	
	public SubscriptionExpiredException() {
		super();
	}

	public SubscriptionExpiredException(String message) {
		super(message);
	}
}

package io.isamm.projectsmanagement.exceptions;


public class WeakPasswordException extends BadRequestException {

	private static final long serialVersionUID = 3472681870885090027L;

	public WeakPasswordException() {
		super();
	}

	public WeakPasswordException(Throwable cause) {
		super(cause);
	}
	
}

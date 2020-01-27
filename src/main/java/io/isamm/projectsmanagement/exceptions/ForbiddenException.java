package io.isamm.projectsmanagement.exceptions;


public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = -1008838663542711244L;

	public ForbiddenException() {
		super();
	}

	public ForbiddenException(Throwable cause) {
		super(cause);
	}
	
}

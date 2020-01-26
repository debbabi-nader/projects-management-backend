package io.isamm.projectsmanagement.exceptions;


public class UniqueConstraintViolationException extends BadRequestException {

	private static final long serialVersionUID = 535080170580298920L;

	public UniqueConstraintViolationException() {
		super();
	}

	public UniqueConstraintViolationException(Throwable cause) {
		super(cause);
	}
		
}

package io.isamm.projectsmanagement.exceptions;


public class AccountNotActiveException extends ForbiddenException {

	private static final long serialVersionUID = -2226372619154766143L;

	public AccountNotActiveException() {
		super();
	}

	public AccountNotActiveException(Throwable cause) {
		super(cause);
	}

}

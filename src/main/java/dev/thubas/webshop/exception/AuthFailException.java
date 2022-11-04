package dev.thubas.webshop.exception;

public class AuthFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthFailException() {
		super();
	}

	public AuthFailException(String message) {
		super(message);
	}

}

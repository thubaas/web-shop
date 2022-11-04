package dev.thubas.webshop.exception;

public class CartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CartNotFoundException() {
		super();
	}

	public CartNotFoundException(String message) {
		super(message);
	}
	
}

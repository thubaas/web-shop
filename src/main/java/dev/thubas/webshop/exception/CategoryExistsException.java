package dev.thubas.webshop.exception;

public class CategoryExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryExistsException() {
		super();
	}

	public CategoryExistsException(String message) {
		super(message);
	}

}

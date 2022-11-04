package dev.thubas.webshop.exception;

public class ProductExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductExistsException() {
		super();
	}

	public ProductExistsException(String message) {
		super(message);
	}
	

}

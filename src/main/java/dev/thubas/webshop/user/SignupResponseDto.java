package dev.thubas.webshop.user;

public class SignupResponseDto {

	private String message;

	public SignupResponseDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

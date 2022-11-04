package dev.thubas.webshop.user;

public class SigninResponseDto {

	private String message;
	private String token;

	public SigninResponseDto() {
		super();
	}

	public SigninResponseDto(String message, String token) {
		super();
		this.message = message;
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

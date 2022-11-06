package dev.thubas.webshop.user;

public class SigninResponseDto {

	private String message;
	private String token;
	private Long id;

	public SigninResponseDto() {
		super();
	}

	public SigninResponseDto(String message, String token, Long id) {
		super();
		this.message = message;
		this.token = token;
		this.id = id;
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
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}

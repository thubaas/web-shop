package dev.thubas.webshop.user;

import javax.validation.constraints.NotBlank;

public class SigninDto {

	@NotBlank(message = "User email is required")
	private String email;

//	@Min(value = 8)
	@NotBlank(message = "User password is required")
	private String password;

	public SigninDto() {
		super();
	}

	public SigninDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

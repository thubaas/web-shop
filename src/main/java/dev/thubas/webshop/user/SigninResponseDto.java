package dev.thubas.webshop.user;

public class SigninResponseDto {

	private String username;
	private String token;
	private Long id;
	private String role;

	public SigninResponseDto() {
		super();
	}

	public SigninResponseDto(String username, String token, Long id, String role) {
		super();
		this.username = username;
		this.token = token;
		this.id = id;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}

}

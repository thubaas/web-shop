package dev.thubas.webshop.user;

public class SignupDto {
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String role;

	public SignupDto() {
		super();
	}

	public SignupDto(
			String firstname, 
			String lastname, 
			String email, 
			String password, 
			String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SignupDto [firstname=" + firstname 
				+ ", lastname=" + lastname 
				+ ", email=" + email 
				+ ", password=" + password 
				+ ", role=" + role
				+ "]";
	}

}

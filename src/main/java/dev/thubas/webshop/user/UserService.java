package dev.thubas.webshop.user;

public interface UserService {
	
	void signup(SignupDto signupDto);
	
	SigninResponseDto signin(SigninDto signinDto);

}

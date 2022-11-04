package dev.thubas.webshop.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(
			@Valid @RequestBody SignupDto signupDto) {
		userService.signup(signupDto);
		SignupResponseDto response = new SignupResponseDto(
				"User created successfully");
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<SigninResponseDto> signin(
			@Valid @RequestBody SigninDto signinDto) {
		SigninResponseDto response = userService.signin(signinDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

}

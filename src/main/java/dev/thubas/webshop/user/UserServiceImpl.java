package dev.thubas.webshop.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.exception.UserExistsException;
import dev.thubas.webshop.auth.AuthService;
import dev.thubas.webshop.auth.AuthToken;
import dev.thubas.webshop.exception.ApiException;
import dev.thubas.webshop.exception.AuthFailException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthService authService;

	@Override
	public void signup(SignupDto signupDto) {

		if (userRepository.existsByEmail(signupDto.getEmail())) {
			throw new UserExistsException("User email is already in use");
		}

		User user = new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstname(signupDto.getFirstname());
		user.setLastname(signupDto.getLastname());
		try {
			user.setPassword(hashPassword(signupDto.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ApiException(e.getMessage());
		}
		User savedUser = userRepository.save(user);
		AuthToken token = new AuthToken(savedUser);
		authService.saveConfirmationToken(token);

	}

	@Override
	public SigninResponseDto signin(SigninDto signinDto) {
		User user = userRepository.findByEmail(signinDto.getEmail())
				.orElseThrow(() -> new AuthFailException("User with email " + signinDto.getEmail() + " not found"));

		String hashedLoginPassword;
		try {
			hashedLoginPassword = hashPassword(signinDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ApiException(e.getMessage());
		}

		if (!user.getPassword().equals(hashedLoginPassword)) {
			throw new AuthFailException("Invalid Credentials");
		}
		
		String token = authService.getToken(user).getToken();
		SigninResponseDto signinResponseDto = new SigninResponseDto("Signed in successfully", token);
		return signinResponseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

}

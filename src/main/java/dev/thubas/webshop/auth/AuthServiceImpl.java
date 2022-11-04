package dev.thubas.webshop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.exception.AuthFailException;
import dev.thubas.webshop.user.User;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthTokenRepository authTokenRepository;

	public void saveConfirmationToken(AuthToken authToken) {
		authTokenRepository.save(authToken);
	}

	@Override
	public User getUser(String tokenValue) {
		AuthToken token = authTokenRepository.findByToken(tokenValue);
		if (token == null || token.getUser() == null) {
			throw new AuthFailException("User not found");
		}
		return token.getUser();
	}

	@Override
	public void authenticate(String token) {
		AuthToken authToken = authTokenRepository.findByToken(token);
		if (authToken == null || getUser(token) == null) {
			throw new AuthFailException("Invalid Token");
		}
	}

	@Override
	public AuthToken getToken(User user) {
		return authTokenRepository.findByUser(user);
	}

	@Override
	public User getAuthenticatedUser(String tokenValue) {
		AuthToken token = authTokenRepository.findByToken(tokenValue);
		if (token == null || token.getUser() == null) {
			throw new AuthFailException("User not found");
		}
		return token.getUser();
	}

}

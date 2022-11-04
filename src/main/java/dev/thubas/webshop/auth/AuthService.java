package dev.thubas.webshop.auth;

import dev.thubas.webshop.user.User;

public interface AuthService {
	
	void saveConfirmationToken(AuthToken authToken);

	User getUser(String tokenValue);

	void authenticate(String token);

	AuthToken getToken(User user);

	User getAuthenticatedUser(String tokenValue);

}

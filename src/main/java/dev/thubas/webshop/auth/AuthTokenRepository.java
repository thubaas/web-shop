package dev.thubas.webshop.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.thubas.webshop.user.User;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>{

	AuthToken findByUser(User user);
	AuthToken findByToken(String token);
}

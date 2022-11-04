package dev.thubas.webshop.wishlist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.thubas.webshop.user.User;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
		List<Wishlist> findAllByUserOrderByCreationDateDesc(User user);
}

package dev.thubas.webshop.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.thubas.webshop.user.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByUserOrderByCreationDateDesc(User user);

}

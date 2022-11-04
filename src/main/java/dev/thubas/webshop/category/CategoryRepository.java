package dev.thubas.webshop.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByName(String name);

	Category findByName(String name);

}

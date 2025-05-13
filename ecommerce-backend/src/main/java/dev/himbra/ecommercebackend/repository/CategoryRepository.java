package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

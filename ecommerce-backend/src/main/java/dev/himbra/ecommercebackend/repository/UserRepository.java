package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.Product;
import dev.himbra.ecommercebackend.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT p FROM Wishlist w JOIN w.product p WHERE w.user.id = :userId")
    Page<Product> findWishlistProductsByUserId(@Param("userId") String userId, Pageable pageable);
}

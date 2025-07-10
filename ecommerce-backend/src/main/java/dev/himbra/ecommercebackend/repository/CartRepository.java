package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser_Id(String userId);
    Optional<Cart> findByGuestId(String guestId);
}

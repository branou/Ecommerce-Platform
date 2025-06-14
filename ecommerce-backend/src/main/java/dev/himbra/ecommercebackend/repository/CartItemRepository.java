package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCart_IdAndProduct_Id(Long cartId, Long productId);

    List<CartItem> findByCart_Id(long cartId);
}

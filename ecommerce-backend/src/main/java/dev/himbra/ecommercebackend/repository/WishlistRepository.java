package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.User;
import dev.himbra.ecommercebackend.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    Optional<Wishlist> getByUser_Id(String userId);
}

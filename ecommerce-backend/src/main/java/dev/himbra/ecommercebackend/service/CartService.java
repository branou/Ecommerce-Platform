package dev.himbra.ecommercebackend.service;

import dev.himbra.ecommercebackend.model.Cart;
import dev.himbra.ecommercebackend.model.CartItem;
import dev.himbra.ecommercebackend.model.Product;
import dev.himbra.ecommercebackend.model.User;
import dev.himbra.ecommercebackend.repository.CartItemRepository;
import dev.himbra.ecommercebackend.repository.CartRepository;
import dev.himbra.ecommercebackend.repository.ProductRepository;
import dev.himbra.ecommercebackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    // Add methods to handle cart operations, such as adding items, removing items, and checking out.
    // Example method to add an item to the cart
    @Transactional
    public void addCartItemToCart(Long productId, int quantity,String guestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            String userId = jwt.getClaim("sub");
            // Load User entity
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
            //  Load Product entity
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
            // Find the cart for the user
            Cart cart = cartRepository.findByUserId(userId)
                    .orElseGet(()-> {
                        Cart newCart = Cart.builder()
                                .user(user).build();
                        return cartRepository.save(newCart);
                    });

            // Check if the product is already in the cart
            Optional<CartItem> cartItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
            if (cartItemOpt.isPresent()) {
                // 3. If exists, update quantity
                CartItem existingItem = cartItemOpt.get();
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cartItemRepository.save(existingItem);
            } else {
                // 4. If not exists, create new CartItem
                CartItem newItem = CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(quantity)
                        .build();
                cartItemRepository.save(newItem);
            }
        }
        else {
            // Load or create cart
            Cart cart = cartRepository.findByGuestId(guestId)
                    .orElseGet(() -> cartRepository.save(Cart.builder().guestId(guestId).build()));
            // Load product
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            // Find or create cart item
            Optional<CartItem> cartItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

            if (cartItemOpt.isPresent()) {
                CartItem existing = cartItemOpt.get();
                existing.setQuantity(existing.getQuantity() + quantity);
                cartItemRepository.save(existing);
            } else {
                CartItem newItem = CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(quantity)
                        .build();
                cartItemRepository.save(newItem);
            }
        }
        }
    public void transferGuestCartToUser(String guestId, String userId) {
        Optional<Cart> guestCartOpt = cartRepository.findByGuestId(guestId);
        Optional<Cart> userCartOpt = cartRepository.findByUserId(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        if (guestCartOpt.isPresent()) {
            Cart guestCart = guestCartOpt.get();
            Cart userCart = userCartOpt.orElseGet(() -> {
                Cart newCart = Cart.builder().user(user).build();
                return cartRepository.save(newCart);
            });

            for (CartItem guestItem : guestCart.getItems()) {
                Optional<CartItem> existing = cartItemRepository
                        .findByCartIdAndProductId(userCart.getId(), guestItem.getProduct().getId());

                if (existing.isPresent()) {
                    CartItem item = existing.get();
                    item.setQuantity(item.getQuantity() + guestItem.getQuantity());
                    cartItemRepository.save(item);
                } else {
                    CartItem newItem = CartItem.builder()
                            .cart(userCart)
                            .product(guestItem.getProduct())
                            .quantity(guestItem.getQuantity())
                            .build();
                    cartItemRepository.save(newItem);
                }
            }

            // Cleanup guest cart
            cartItemRepository.deleteAll(guestCart.getItems());
            cartRepository.delete(guestCart);
        }
    }


}




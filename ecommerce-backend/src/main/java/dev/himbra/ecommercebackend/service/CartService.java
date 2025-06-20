package dev.himbra.ecommercebackend.service;

import dev.himbra.ecommercebackend.dto.CartItemResponse;
import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.mapper.CartItemMapper;
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
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;
    // Add methods to handle cart operations, such as adding items, removing items, and checking out.
    // Example method to add an item to the cart
    public List<CartItemResponse> getCartItems(long cartId) {
        List<CartItem> items = cartItemRepository.findByCart_Id(cartId);
        return items.stream()
                .map(cartItemMapper::toCartItemResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public void addCartItemToCart(ProductRequest productRequest, int quantity, String guestId) {
        Product product = productRepository.findByName(productRequest.name())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with name: " + productRequest.name()));
        Cart cart = getOrCreateCart(guestId);
        addOrUpdateCartItem(cart, product, quantity);
    }

    private Cart getOrCreateCart(String guestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            String userId = jwtAuth.getToken().getClaim("sub");

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

            return cartRepository.findByUserId(userId)
                    .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));
        }

        return cartRepository.findByGuestId(guestId)
                .orElseGet(() -> cartRepository.save(Cart.builder().guestId(guestId).build()));
    }

    private void addOrUpdateCartItem(Cart cart, Product product, int quantity) {
        Optional<CartItem> cartItemOpt = cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(), product.getId());

        if (cartItemOpt.isPresent()) {
            CartItem existingItem = cartItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cartItemRepository.save(newItem);
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
                        .findByCart_IdAndProduct_Id(userCart.getId(), guestItem.getProduct().getId());

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


    public void removeCartItemFromCart(Long productId, String guestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cart cart;

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            String userId = jwtAuth.getToken().getClaim("sub");
            cart = cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Cart not found for user ID: " + userId));
        } else {
            cart = cartRepository.findByGuestId(guestId)
                    .orElseThrow(() -> new EntityNotFoundException("Cart not found for guest ID: " + guestId));
        }

        CartItem cartItem = cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(), productId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found for product ID: " + productId));

        cartItemRepository.delete(cartItem);
    }

}




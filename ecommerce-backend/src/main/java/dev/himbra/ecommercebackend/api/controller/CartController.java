package dev.himbra.ecommercebackend.api.controller;

import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.model.Product;
import dev.himbra.ecommercebackend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody ProductRequest product, @RequestParam int quantity, @RequestParam(required = false) String guestId) {
        // Logic to add a product to the cart
        cartService.addCartItemToCart(product, quantity, guestId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/removeFromCart/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId,@RequestParam(required = false) String guestId) {
        // Logic to remove a product from the cart
        cartService.removeCartItemFromCart(productId,guestId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/items/{cartId}")
    public ResponseEntity<?> getCartItems(@PathVariable long cartId) {
        // Logic to get all items in the cart
        return ResponseEntity.ok(cartService.getCartItems(cartId));
    }

}
